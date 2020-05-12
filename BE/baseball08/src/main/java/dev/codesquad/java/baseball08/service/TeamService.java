package dev.codesquad.java.baseball08.service;

import dev.codesquad.java.baseball08.dao.TeamDao;
import dev.codesquad.java.baseball08.dto.ResponsePlayersDto;
import dev.codesquad.java.baseball08.dto.TempDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private static final Logger logger = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    private TeamDao teamDao;

    public List<ResponsePlayersDto> teamPlayerInfo(Long teamId) {
        Long oppositTeamId = teamDao.findOppositTeamByTeamId(teamId);
        List<ResponsePlayersDto> responsePlayersDtos = new ArrayList<>();
        responsePlayersDtos.add(teamDao.findTeamPlayerInfo(teamId));
        responsePlayersDtos.add(teamDao.findTeamPlayerInfo(oppositTeamId));
        return responsePlayersDtos;
    }
}
