package dev.codesquad.java.baseball08.service;

import dev.codesquad.java.baseball08.dao.TeamDao;
import dev.codesquad.java.baseball08.dao.TeamDao2;
import dev.codesquad.java.baseball08.dao.henry.GameDao2;
import dev.codesquad.java.baseball08.dto.PlayerInfoDto;
import dev.codesquad.java.baseball08.dto.ResponsePlayersDto;
import dev.codesquad.java.baseball08.dto.henry.AvailabilityResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private static final Logger logger = LoggerFactory.getLogger(TeamService.class);

    private final TeamDao teamDao;
    private final TeamDao2 teamDao2;

    public List<ResponsePlayersDto> teamPlayerInfo(Long teamId) {
        Long oppositTeamId = teamDao.findOppositTeamByTeamId(teamId);
        List<ResponsePlayersDto> responsePlayersDtos = new ArrayList<>();
        responsePlayersDtos.add(teamDao.findTeamPlayerInfo(teamId));
        responsePlayersDtos.add(teamDao.findTeamPlayerInfo(oppositTeamId));
        return responsePlayersDtos;
    }

    public ResponsePlayersDto getTeamPlayersInfo(Long id) {
        PlayerInfoDto playerInfoDto = teamDao2.findTeamById(id).orElseThrow(null);
        return new ResponsePlayersDto(playerInfoDto);
    }

    public AvailabilityResponse isTeamAvailable(Long id) {
        String userId = teamDao2.findUserById(id);
        if (userId != null) {
            return new AvailabilityResponse(false);
        }
        return new AvailabilityResponse(true);
    }
}
