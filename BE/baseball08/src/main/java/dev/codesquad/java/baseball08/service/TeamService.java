package dev.codesquad.java.baseball08.service;

import dev.codesquad.java.baseball08.dao.TeamDao;
import dev.codesquad.java.baseball08.dao.TeamDao2;
import dev.codesquad.java.baseball08.dto.PlayerInfoDto;
import dev.codesquad.java.baseball08.dto.AvailableDto;
import dev.codesquad.java.baseball08.dto.ResponsePlayersDto;
import dev.codesquad.java.baseball08.dto.henry.AvailabilityResponse;
import dev.codesquad.java.baseball08.dto.henry.PlayerLogDto;
import dev.codesquad.java.baseball08.dto.henry.TeamScoreResponse;
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
    private TeamDao teamDao;

    @Autowired
    private TeamDao2 teamDao2;

    public List<ResponsePlayersDto> teamPlayerInfo(Long id) {
        Long oppositeTeamId = teamDao.findOppositeTeamByTeamId(id);
        List<ResponsePlayersDto> responsePlayersDtos = new ArrayList<>();
        responsePlayersDtos.add(teamDao.findTeamPlayerInfo(id));
        responsePlayersDtos.add(teamDao.findTeamPlayerInfo(oppositeTeamId));
        return responsePlayersDtos;
    }

    public ResponsePlayersDto getTeamPlayersInfo(Long id) {
        PlayerInfoDto playerInfoDto = teamDao2.findTeamById(id).orElseThrow(null);
        return new ResponsePlayersDto(playerInfoDto);
    }

    public AvailableDto isTeamAvailable2(Long game, Long id) {
        try {
            Optional.ofNullable(teamDao.findUserIdByGameIdTeamId(game, id)).orElseThrow(NullPointerException::new);
            return new AvailableDto(false);
        } catch (NullPointerException e) {
            return new AvailableDto(true);
        }
    }

        public AvailabilityResponse isTeamAvailable (Long id){
            String userId = teamDao2.findUserById(id);
            if (userId != null) {
                return new AvailabilityResponse(false);
            }
            return new AvailabilityResponse(true);
        }

        public TeamScoreResponse getTeamScore (Long id){
            return teamDao2.findHomeTeamScoreById(id);
        }

        public List<PlayerLogDto> getPlayerLog (Long id){
            return teamDao2.findHistoriesById(id);
        }
    }
