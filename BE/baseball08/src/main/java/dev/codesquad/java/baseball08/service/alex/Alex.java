package dev.codesquad.java.baseball08.service.alex;

import dev.codesquad.java.baseball08.dao.GameDaoAlex;
import dev.codesquad.java.baseball08.dao.PitchDaoAlex;
import dev.codesquad.java.baseball08.dao.TeamDaoAlex;
import dev.codesquad.java.baseball08.dto.dto.PitcherDto;
import dev.codesquad.java.baseball08.dto.dto.PlayballDto;
import dev.codesquad.java.baseball08.dto.dto.PlayersDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class Alex {
    private Logger logger = LoggerFactory.getLogger(Alex.class);

    @Autowired
    private GameDaoAlex gameDaoAlex;

    @Autowired
    private TeamDaoAlex teamDaoAlex;

    @Autowired
    private PitchDaoAlex pitchDaoAlex;

    // 피치는 수비만 할 수 있다.
    // 내가 수비인지 아닌지를 판별할 수 있는 근거는?
    // teamId를 보낸 사람이 home인지 away인지 판단을 하고, 공격을 했는지 안했는지를 판단하면 알 수 있겠지?
    // 원정팀은 왼쪽에, 홈팀은 오른쪽에 표시된다.
    // 홈팀 ID로 들어왔는데, 원정팀 공격이 -> true 홈팀 공격 -> true면 Error
    // 홈팀 ID로 들어왔는데, 원정팀 공격 -> true 홈팀 공격 -> false면 OK
    // 원정팀 ID로 들어왔는데, 원정팀 공격이 -> true 홈팀 공격 -> false Error
    // 원정팀 ID로 들어왔는데, 원정팀 공격이 -> true 홈팀 공격 -> true OK
    // 회 초 == awayAttack = true // homeAttack = false
    // 회 말 == awayAttack = true // homeAttack = true

    public boolean pitch(Long teamId) {
        Long gameId = gameDaoAlex.getGameIdByTeamId(teamId);
        PlayballDto playballDto = pitchDaoAlex.findDataToPlayGame(gameId);
        String requestTeamName = teamDaoAlex.findTeamNameByTeamId(teamId);
        if (!playballDto.isTopBottom()) {
            return topInning(playballDto, requestTeamName, teamId);
        }
        return bottomInning(playballDto, requestTeamName, teamId);
    }

    private boolean topInning(PlayballDto playballDto, String requestTeamName, Long teamId) {
        if (requestTeamName.equals(playballDto.getAwayName())) {
            return false;
        }
        Long otherTeamId = teamDaoAlex.findOppositeTeamIdByMyTeamId(teamId);
        PitcherDto currentPitcher = pitchDaoAlex.findPitcherByTeamId(teamId);
        String currentHitter = teamDaoAlex.findHitterByTeamId(otherTeamId)[0];
        String lastHitter = teamDaoAlex.findHitterByTeamId(otherTeamId)[1];
        PlayersDto currentHitterInfo = teamDaoAlex.findPlayerByPlayerName(currentHitter);

        if (!currentHitter.equals(lastHitter)) {
            int atBat = currentHitterInfo.getAtBat();
            currentHitterInfo.setAtBat(atBat + 1);
        }
        logger.info("currentHitterInfo : {}", currentHitterInfo.toString());

        // 투수의 투구수를 하나 증가시킨다.
        int pitches = currentPitcher.getPitches();
        currentPitcher.setPitches(pitches + 1);

        // 타자가 공을 한번 치는 동작을 playball이라는 함수안에 넣어서 진행하였다.
        // 결과는 "S" , "B" , "H" 3가지로 나온다 -> 스트라이크 , 볼 , 안타
        String pitcheResult = playball(currentHitterInfo);
        logger.info("pitchResult : {}", pitcheResult);

        // 공의 결과를 이닝데이터에 반영시켜야 한다.
        int strikeCount = playballDto.getStrikeCount();
        int ballCount = playballDto.getBallCount();
        int outCount = playballDto.getOutCount();
        int baseCount = playballDto.getBaseCount();
        int away_score = playballDto.getAwayScore();
        int away_total_score = playballDto.getAwayTotalScore();

        switch (pitcheResult) {
            case "S":
                strikeCount++;
                break;
            case "B":
                ballCount++;
                break;
            case "H":
                baseCount++;
                break;
        }

        if (strikeCount == 3) {
            outCount++;
            strikeCount = 0;
        }
        if (ballCount == 4) {
            baseCount++;
            ballCount = 0;
        }
        if (baseCount == 4) {
            away_score++;
            away_total_score++;
            baseCount--;
        }
        if (outCount == 3) {
            strikeCount = 0;
            ballCount = 0;
            baseCount = 0;
        }

        logger.info("strike count : {}", strikeCount);
        logger.info("ball count : {}", ballCount);
        logger.info("out count : {}", outCount);
        logger.info("base count : {}", baseCount);
        logger.info("-------------------------------------");
        logger.info("away_score : {}", away_score);
        logger.info("away_total_score : {}", away_total_score);

        playballDto.setStrikeCount(strikeCount);
        playballDto.setBallCount(ballCount);
        playballDto.setOutCount(outCount);
        playballDto.setBaseCount(baseCount);
        playballDto.setAwayScore(away_score);
        playballDto.setAwayTotalScore(away_total_score);

        pitchDaoAlex.savePitchResult(playballDto);
        // 지금부터 구현해야 할 부분
        // 이 메소드는 공을 한번 던졌을때의 메소드 이다.
        // 1. current_hitter에 해당하는 player DB에 atBat += 1 , pitches += 1

        // 2. 공을 치는 확률을 계산한다.
        // 3. 안타의 경우 -> player DB에 hit += 1 , inning DB에 base_count += 1 // 만약 base_count == 4 이면, inning DB home_score += 1 , game DB home_total_score += 1
        // 4. 스트라이크의 경우 -> inning DB에 strike_count += 1 // 만약 strike_count == 3 이면 out_count += 1 && strike_count = 0

        // 5. 아웃의 경우 -> out_count == 3 이면 이닝이 변경되어야 한다. -> top_bottom값을 true로 변경한다. '
        // 아웃이 되면 current_hitter의 데이터를 hitters의 다음 타자로 가져와야 한다. 만약 9번이 out이면 1번으로 돌아가야 한다.

        // 아웃의 경우 게임이 종료된 것을 뜻하니까!! -> 새로운 이닝을 DB에 추가한다
        // 아웃이 아닌 경우 공 하나의 결과가 존재하니까 해당 데이터를 DB에 그대로 저장한다.
        return true;
    }

    private boolean bottomInning(PlayballDto playballDto, String requestTeamName, Long teamId) {
        if (requestTeamName.equals(playballDto.getHomeName())) {
            return false;
        }
        return true;
    }

    private String playball(PlayersDto currentHitterInfo) {
        String strike = "S";
        String ball = "B";
        String hit = "H";

        double average = currentHitterInfo.getAverage();
        double strike_percent = 0.7;
        if (strike_percent < ThreadLocalRandom.current().nextDouble(0, 1)) {
            return ball;
        }

        if (average < ThreadLocalRandom.current().nextDouble(0, 1)) {
            return strike;
        }

        return hit;
    }
}
