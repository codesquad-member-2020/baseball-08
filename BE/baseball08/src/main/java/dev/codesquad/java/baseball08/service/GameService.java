package dev.codesquad.java.baseball08.service;

import dev.codesquad.java.baseball08.dao.GameDaoAlex;
import dev.codesquad.java.baseball08.dto.dto.AvailableDto;
import dev.codesquad.java.baseball08.dao.GameDaoHenry;
import dev.codesquad.java.baseball08.dto.response.AvailabilityResponse;
import dev.codesquad.java.baseball08.dto.response.GameListResponse;
import dev.codesquad.java.baseball08.dto.response.GamePlayResponse;
import dev.codesquad.java.baseball08.dto.dto.StageDto;
import dev.codesquad.java.baseball08.dto.response.TeamScoreResponse;
import dev.codesquad.java.baseball08.entity.Inning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private Logger logger = LoggerFactory.getLogger(GameService.class);

    @Autowired
    private GameDaoHenry gameDaoHenry;

    @Autowired
    private GameDaoAlex gameDaoAlex;

    @Autowired
    private TeamService teamService;

    public AvailableDto isGamePossible(Long game) {
        return new AvailableDto(!gameDaoAlex.getGameUserId(game).contains(null));
    }

    public void saveNewInning() {
        int gameKey = gameDaoAlex.getGameKeyForInning(1L);
        List<String> teamNames = gameDaoAlex.getTeamNamesByGameId(1L);
        Inning inning = Inning.builder()
                .awayName(teamNames.get(1)).homeName(teamNames.get(0))
                .awayScore(0).homeScore(0)
                .strikeCount(0).ballCount(0).outCount(0).baseCount(0)
                .game(1L)
                .game_key(gameKey)
                .build();
        gameDaoAlex.saveInning(inning);
    }

    public List<TeamScoreResponse> getGameScore(Long gameId) {
        List<TeamScoreResponse> gameScoreResponse = new ArrayList<>();
        List<Long> teamIds = gameDaoAlex.getGameTeamId(gameId);
        gameScoreResponse.add(teamService.getAwayTeamScore(teamIds.get(0)));
        gameScoreResponse.add(teamService.getHomeTeamScore(teamIds.get(1)));
        return gameScoreResponse;
    }

    public List<StageDto> getGameInfo() {
        return gameDaoAlex.getGameInfo();
    }

    public void updateInning(Long game, String teamName) {

    }

//    public void saveHistory() {
//        int gameKey = gameDao.getHistoryCount(1L);
//        History history = new History("test", 1, "S", 1L, gameKey);
//        gameDao.saveHistory(history);
//    }

    //--------------------------------------------------------------------------------------------------------------------//

    public GamePlayResponse getGamePlay(Long id, Long teamId) {
        GamePlayResponse gamePlayResponse = gameDaoHenry.findGameInfoById(id);
        GamePlayResponse gamePlayResponse2 = gameDaoHenry.findGameTeamInfoById(id, teamId);
        gamePlayResponse.setUser(gamePlayResponse2.getUser());
        gamePlayResponse.setPitcher(gamePlayResponse2.getPitcher());
        gamePlayResponse.setHitter(gamePlayResponse2.getHitter());
        gamePlayResponse.setHistory(gamePlayResponse2.getHistory());
        //return gameDao2.findGameInfoById(id);
        return gamePlayResponse;
    }

    public GamePlayResponse getGamePlay2(Long id, Long awayId, Long homeId) {
        Long tempAwayId = 1L; // findAwayIdByGameId
        Long teampHomeId = 2L; // findHomeIdByGameId
        String turn = "초"; // findTurnByGame

        if (turn.equals("초")) {
            return gameDaoHenry.findGameInfoById(id, tempAwayId, teampHomeId);
        }
        return gameDaoHenry.findGameInfoById(id, teampHomeId, tempAwayId);
    }

    public AvailabilityResponse isGameAvailable(Long id) {
        return gameDaoHenry.IsGameAvailable(id);
    }

    public List<GameListResponse> getGameList() {
        return gameDaoHenry.findAllGame();
    }

}
