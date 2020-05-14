package dev.codesquad.java.baseball08.service.alex;

import dev.codesquad.java.baseball08.dao.GameDaoAlex;
import dev.codesquad.java.baseball08.dao.PitchDaoAlex;
import dev.codesquad.java.baseball08.dao.TeamDaoAlex;
import dev.codesquad.java.baseball08.dto.dto.PitcherDto;
import dev.codesquad.java.baseball08.dto.dto.PlayballDto;
import dev.codesquad.java.baseball08.dto.dto.PlayersDto;
import dev.codesquad.java.baseball08.entity.Inning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class Alex {
    private Logger logger = LoggerFactory.getLogger(Alex.class);

    @Autowired
    private GameDaoAlex gameDaoAlex;

    @Autowired
    private TeamDaoAlex teamDaoAlex;

    @Autowired
    private PitchDaoAlex pitchDaoAlex;

    public boolean pitch(Long teamId) {
        Long gameId = gameDaoAlex.getGameIdByTeamId(teamId);
        PlayballDto playballDto = pitchDaoAlex.findDataToPlayGame(gameId);
        String requestTeamName = teamDaoAlex.findTeamNameByTeamId(teamId);
        logger.info("requestName = {}",requestTeamName);
        if (!playballDto.isTopBottom()) {
            return topInning(playballDto, requestTeamName, teamId);
        }
        return bottomInning(playballDto, requestTeamName, teamId);
    }

    private boolean topInning(PlayballDto playballDto, String requestTeamName, Long teamId) {
        if (requestTeamName.equals(playballDto.getAwayName())) {
            return false;
        }
        logger.info("----------------------------------------------------------");
        logger.info(" 공 한번 던지기 시작!!!!!!!");
        Long otherTeamId = teamDaoAlex.findOppositeTeamIdByMyTeamId(teamId);
        PitcherDto currentPitcher = pitchDaoAlex.findPitcherByTeamId(teamId);
        logger.info("현재 투수 : {}",currentPitcher.getName());
        logger.info("현재 투수 투구수 : {}",currentPitcher.getPitches());
        String currentHitter = teamDaoAlex.findHitterByTeamId(otherTeamId)[0];
        String lastHitter = teamDaoAlex.findHitterByTeamId(otherTeamId)[1];
        PlayersDto currentHitterInfo = teamDaoAlex.findPlayerByPlayerName(currentHitter);
        boolean playerChange = false;

        logger.info("현재 타자 = {}",currentHitter);
        logger.info("지난 타자 = {}",lastHitter);

        if (!currentHitter.equals(lastHitter)) {
            int atBat = currentHitterInfo.getAtBat();
            currentHitterInfo.setAtBat(atBat + 1);
        }
        logger.info("현재 타자 성적 : {}", currentHitterInfo.toString());

        // 투수의 투구수를 하나 증가시킨다.
        int pitches = currentPitcher.getPitches();
        currentPitcher.setPitches(pitches + 1);

        // 타자가 공을 한번 치는 동작을 playball이라는 함수안에 넣어서 진행하였다.
        // 결과는 "S" , "B" , "H" 3가지로 나온다 -> 스트라이크 , 볼 , 안타
        String pitcheResult = playball(currentHitterInfo);
        logger.info("투구 결과값 : {}", pitcheResult);
        // 공의 결과를 이닝데이터에 반영시켜야 한다.
        int strikeCount = playballDto.getStrikeCount();
        int ballCount = playballDto.getBallCount();
        int outCount = playballDto.getOutCount();
        int baseCount = playballDto.getBaseCount();
        int away_score = playballDto.getAwayScore();
        int away_total_score = playballDto.getAwayTotalScore();
        boolean topBottom = playballDto.isTopBottom();

        switch (pitcheResult) {
            case "S":
                logger.info("스트라이크");
                strikeCount++;
                break;
            case "B":
                logger.info("볼볼볼볼");
                ballCount++;
                break;
            case "H":
                logger.info("안타안타");
                baseCount++;
                int hitterOutCount = currentHitterInfo.getHit();
                currentHitterInfo.setHit(hitterOutCount + 1);
                strikeCount = 0;
                ballCount = 0;
                playerChange = true;
                break;
        }

        if (strikeCount == 3) {
            logger.info("아웃아웃");
            outCount++;
            int hitterOutCount = currentHitterInfo.getOut();
            currentHitterInfo.setOut(hitterOutCount + 1);
            strikeCount = 0;
            ballCount = 0;
            playerChange = true;
        }

        if (ballCount == 4) {
            baseCount++;
            strikeCount = 0;
            ballCount = 0;
            playerChange = true;
        }

        if (baseCount == 4) {
            away_score++;
            away_total_score++;
            baseCount--;
        }
        if (outCount == 3) {
            strikeCount = 0;
            ballCount = 0;
            outCount = 0;
            baseCount = 0;
            topBottom = true;
            playerChange = true;
            pitchDaoAlex.updateInningInfo(topBottom,playballDto.getGameId());
            logger.info("경기 종료!!!!!!!!!!!!!!!!!!!!!");
            logger.info("경기 종료!!!!!!!!!!!!!!!!!!!!!");
        }

        logger.info("strike count : {}", strikeCount);
        logger.info("ball count : {}", ballCount);
        logger.info("out count : {}", outCount);
        logger.info("base count : {}", baseCount);
        logger.info("away_score : {}", away_score);
        logger.info("pitches : {}",currentPitcher.getPitches());
        logger.info("away_total_score : {}", away_total_score);
        logger.info("현재 타자 결과 값 : {}",currentHitterInfo);

        playballDto.setStrikeCount(strikeCount);
        playballDto.setBallCount(ballCount);
        playballDto.setOutCount(outCount);
        playballDto.setBaseCount(baseCount);
        playballDto.setAwayScore(away_score);
        playballDto.setAwayTotalScore(away_total_score);
        playballDto.setTopBottom(topBottom);

        lastHitter = currentHitter;
        if (playerChange) {
            currentHitter = teamDaoAlex.findNextHitterNameByLineup(otherTeamId,currentHitterInfo.getLineUp());
        }

        pitchDaoAlex.savePitchResult(playballDto);
        pitchDaoAlex.savePlayerResult(currentHitterInfo,otherTeamId);
        pitchDaoAlex.saveTeamInfo(currentHitter,lastHitter,currentPitcher.getPitches(),otherTeamId,teamId);
        logger.info("--------------------------------------------------------");
        return true;
    }

    private boolean bottomInning(PlayballDto playballDto, String requestTeamName, Long teamId) {
        if (requestTeamName.equals(playballDto.getHomeName())) {
            return false;
        }

        logger.info("----------------------------------------------------------");
        logger.info(" 공 한번 던지기 시작!!!!!!!");
        Long otherTeamId = teamDaoAlex.findOppositeTeamIdByMyTeamId(teamId);
        PitcherDto currentPitcher = pitchDaoAlex.findPitcherByTeamId(teamId);
        String currentHitter = teamDaoAlex.findHitterByTeamId(otherTeamId)[0];
        String lastHitter = teamDaoAlex.findHitterByTeamId(otherTeamId)[1];
        PlayersDto currentHitterInfo = teamDaoAlex.findPlayerByPlayerName(currentHitter);
        boolean playerChange = false;

        logger.info("현재 타자 = {}",currentHitter);
        logger.info("지난 타자 = {}",lastHitter);

        if (!currentHitter.equals(lastHitter)) {
            int atBat = currentHitterInfo.getAtBat();
            currentHitterInfo.setAtBat(atBat + 1);
        }
        logger.info("현재 타자 성적 : {}", currentHitterInfo.toString());

        // 투수의 투구수를 하나 증가시킨다.
        int pitches = currentPitcher.getPitches();
        currentPitcher.setPitches(pitches + 1);

        // 타자가 공을 한번 치는 동작을 playball이라는 함수안에 넣어서 진행하였다.
        // 결과는 "S" , "B" , "H" 3가지로 나온다 -> 스트라이크 , 볼 , 안타
        String pitcheResult = playball(currentHitterInfo);
        logger.info("투구 결과값 : {}", pitcheResult);
        // 공의 결과를 이닝데이터에 반영시켜야 한다.
        int strikeCount = playballDto.getStrikeCount();
        int ballCount = playballDto.getBallCount();
        int outCount = playballDto.getOutCount();
        int baseCount = playballDto.getBaseCount();
        int home_score = playballDto.getHomeScore();
        int home_total_score = playballDto.getHomeTotalScore();
        boolean topBottom = playballDto.isTopBottom();

        switch (pitcheResult) {
            case "S":
                logger.info("스트라이크");
                strikeCount++;
                break;
            case "B":
                logger.info("볼볼볼볼");
                ballCount++;
                break;
            case "H":
                logger.info("안타안타");
                baseCount++;
                int hitterOutCount = currentHitterInfo.getHit();
                currentHitterInfo.setHit(hitterOutCount + 1);
                strikeCount = 0;
                ballCount = 0;
                playerChange = true;
                break;
        }

        if (strikeCount == 3) {
            logger.info("아웃아웃");
            outCount++;
            int hitterOutCount = currentHitterInfo.getOut();
            currentHitterInfo.setOut(hitterOutCount + 1);
            strikeCount = 0;
            ballCount = 0;
            playerChange = true;
        }

        if (ballCount == 4) {
            baseCount++;
            strikeCount = 0;
            ballCount = 0;
            playerChange = true;
        }

        if (baseCount == 4) {
            home_score++;
            home_total_score++;
            baseCount--;
        }
        if (outCount == 3) {
            strikeCount = 0;
            ballCount = 0;
            outCount = 0;
            baseCount = 0;
            playerChange = true;
            topBottom = false;
            pitchDaoAlex.updateInningInfo(topBottom,playballDto.getGameId());
            createInning(playballDto);
            logger.info("경기 종료!!!!!!!!!!!!!!!!!!!!!");
            logger.info("경기 종료!!!!!!!!!!!!!!!!!!!!!");
        }

        logger.info("strike count : {}", strikeCount);
        logger.info("ball count : {}", ballCount);
        logger.info("out count : {}", outCount);
        logger.info("base count : {}", baseCount);
        logger.info("home_score : {}", home_score);
        logger.info("pitches : {}",currentPitcher.getPitches());
        logger.info("home_total_score : {}", home_total_score);
        logger.info("현재 타자 결과 값 : {}",currentHitterInfo);

        playballDto.setStrikeCount(strikeCount);
        playballDto.setBallCount(ballCount);
        playballDto.setOutCount(outCount);
        playballDto.setBaseCount(baseCount);
        playballDto.setHomeScore(home_score);
        playballDto.setHomeTotalScore(home_total_score);
        playballDto.setTopBottom(topBottom);

        lastHitter = currentHitter;
        if (playerChange) {
            currentHitter = teamDaoAlex.findNextHitterNameByLineup(otherTeamId,currentHitterInfo.getLineUp());
        }

        pitchDaoAlex.savePitchResult(playballDto);
        pitchDaoAlex.savePlayerResult(currentHitterInfo,otherTeamId);
        pitchDaoAlex.saveTeamInfo(currentHitter,lastHitter,currentPitcher.getPitches(),otherTeamId,teamId);
        logger.info("--------------------------------------------------------");

        return true;
    }

    private String playball(PlayersDto currentHitterInfo) {
        String strike = "S";
        String ball = "B";
        String hit = "H";

        double average = currentHitterInfo.getAverage();
        double strike_percent = 0.8;
        if (strike_percent < ThreadLocalRandom.current().nextDouble(0, 1)) {
            return ball;
        }

        if (average < ThreadLocalRandom.current().nextDouble(0, 1)) {
            return strike;
        }

        return hit;
    }

    private void createInning(PlayballDto playballDto) {
        Long gameId = playballDto.getGameId();
        Integer newGameKey = gameDaoAlex.getGameKeyForInning(gameId);
        gameDaoAlex.updateCurrentInning(gameId, newGameKey + 1);
        String homeName = playballDto.getHomeName();
        String awayName = playballDto.getAwayName();
        Inning inning = Inning.builder().game(gameId).game_key(newGameKey).homeName(homeName).awayName(awayName).build();
        gameDaoAlex.saveInning(inning);
    }
}
