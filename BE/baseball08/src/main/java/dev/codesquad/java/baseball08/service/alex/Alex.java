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
        // PlayballDto에는 경기의 진행에 필요한 데이터들이 들어있다. 선수들의 데이터는 아직 들어있지 않은 상태이다.
        // 상대팀의 teamId를 통해서 타자의 정보를 가져온다.
        Long otherTeamId = teamDaoAlex.findOppositeTeamByTeamId(teamId);
        // 상대팀의 타자 정보가 이번 이닝의 공격 타자 리스트이다.
        List<PlayersDto> hitters = teamDaoAlex.findPlayersByTeamId(otherTeamId);
        // 내 team에 투수와, 투구수를 가져와서 PitcherDto에 저장한다.
        PitcherDto currentPitcher = pitchDaoAlex.findPitcherByTeamId(teamId);
        // 현재 타자의 데이터를 가져온다.
        PlayersDto currentHitter = new PlayersDto();

        for (int i = 0; i < hitters.size(); i++) {
            if (hitters.get(i).getName().equals(pitchDaoAlex.findHitterByTeamId(otherTeamId))) currentHitter = hitters.get(i);
        }

        int hitterAtBat = currentHitter.getAtBat();
        currentHitter.setAtBat(hitterAtBat+1);

        int pitches = currentPitcher.getPitches();
        currentPitcher.setPitches(pitches+1);

        String pitchResult = playball(currentHitter);
        logger.info("{}",pitchResult);
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
        List<String> players = teamDaoAlex.findPlayersNameByTeamId(teamId);
        return true;
    }

    private String playball(PlayersDto cuttrentHitter) {
        String strike = "S";
        String ball = "B";
        String hit = "H";

        double average = cuttrentHitter.getAverage();
        double strike_percent = 0.7;
        if (strike_percent < ThreadLocalRandom.current().nextDouble(0,1)) {
            return ball;
        }

        if (average < ThreadLocalRandom.current().nextDouble(0,1)) {
            return strike;
        }

        return hit;
    }
}
