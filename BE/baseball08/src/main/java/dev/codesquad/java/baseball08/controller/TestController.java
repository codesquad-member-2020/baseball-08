package dev.codesquad.java.baseball08.controller;

import dev.codesquad.java.baseball08.dao.TeamDao2;
import dev.codesquad.java.baseball08.dto.PlayerInfoDto;
import dev.codesquad.java.baseball08.dto.PlayersDto;
import dev.codesquad.java.baseball08.dto.ResponsePlayersDto;
import dev.codesquad.java.baseball08.dto.TempDto;
import dev.codesquad.java.baseball08.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private final TeamService teamService;

    @Autowired
    private TeamDao2 teamDao2;

    @GetMapping("/")
    public ResponseEntity<List<ResponsePlayersDto>>
    test() {
        return new ResponseEntity<>(teamService.teamPlayerInfo(1L), HttpStatus.OK);
    }

    @GetMapping("/alex")
    public String test1() {
        teamService.teamPlayerInfo(1L);
        return "ok";
    }

    @GetMapping("/henry")
    public ResponseEntity test2() {
        PlayerInfoDto playerInfoDto = teamDao2.findTeamById(1L).orElseThrow(null);
        PlayersDto playersDto = new PlayersDto();

//        TotalDto totalDto = new TotalDto(r);
//
//        TeamPlayerResponse teamPlayerResponse = new TeamPlayerResponse(r, playersDto2, totalDto);

        return new ResponseEntity(playersDto.createList(playerInfoDto), HttpStatus.OK);
    }
}
