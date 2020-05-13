package dev.codesquad.java.baseball08.service;

import dev.codesquad.java.baseball08.dao.GameDaoAlex;
import dev.codesquad.java.baseball08.dao.TeamDaoAlex;
import dev.codesquad.java.baseball08.dao.TeamDaoHenry;
import dev.codesquad.java.baseball08.dto.dto.PlayerInfoDto;
import dev.codesquad.java.baseball08.dto.dto.AvailableDto;
import dev.codesquad.java.baseball08.dto.response.PlayersResponse;
import dev.codesquad.java.baseball08.dto.response.AvailabilityResponse;
import dev.codesquad.java.baseball08.dto.dto.PlayerLogDto;
import dev.codesquad.java.baseball08.dto.response.TeamScoreResponse;
import dev.codesquad.java.baseball08.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private static final Logger logger = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    private TeamDaoAlex teamDao;

    @Autowired
    private TeamDaoHenry teamDao2;

    @Autowired
    private GameDaoAlex gameDao;

    public List<PlayersResponse> teamPlayerInfo(Long gameid) {
        List<Long> teamIds = gameDao.getGameTeamId(gameid);
        List<PlayersResponse> playersResponses = new ArrayList<>();
        playersResponses.add(teamDao.findTeamPlayerInfo(teamIds.get(0)));
        playersResponses.add(teamDao.findTeamPlayerInfo(teamIds.get(1)));
        return playersResponses;
    }

    public PlayersResponse getTeamPlayersInfo(Long id) {
        PlayerInfoDto playerInfoDto = teamDao2.findTeamById(id).orElseThrow(null);
        return new PlayersResponse(playerInfoDto);
    }

    public AvailableDto isTeamAvailable2(Long game, Long id) {
        try {
            Optional.ofNullable(teamDao.findUserIdByGameIdTeamId(game, id)).orElseThrow(CustomException::new);
            return new AvailableDto(false);
        } catch (CustomException e) {
            return new AvailableDto(true);
        }
    }

    public AvailabilityResponse isTeamAvailable(String teamName) {
        // 수정 필요!!!
        Long id = 1L;
        String userId = teamDao2.findUserById(id);
        if (userId != null) {
            return new AvailabilityResponse(false);
        }
        return new AvailabilityResponse(true);
    }



    public TeamScoreResponse getTeamScore(Long id) {
        return teamDao2.findHomeTeamScoreById(id);
    }

    public List<PlayerLogDto> getPlayerLog(Long id) {
        return teamDao2.findHistoriesById(id);
    }
}
