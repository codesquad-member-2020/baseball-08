package dev.codesquad.java.baseball08.controller;

import dev.codesquad.java.baseball08.dto.AvailableDto;
import dev.codesquad.java.baseball08.dto.ResponsePlayersDto;
import dev.codesquad.java.baseball08.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
    private final TeamService teamService;

    @GetMapping("/")
    public ResponseEntity<List<ResponsePlayersDto>> test() {
        return new ResponseEntity<>(teamService.teamPlayerInfo(1L), HttpStatus.OK);
    }

    @GetMapping("/alex")
    public String test1() {
        teamService.teamPlayerInfo(1L);
        return "ok";
    }

    @GetMapping("/henry")
    public ResponseEntity test2() {
        return new ResponseEntity(teamService.getTeamPlayersInfo(1L), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<AvailableDto> test3() {
        return new ResponseEntity<>(teamService.isTeamAvailable(1L, 2L), HttpStatus.OK);
    }

}
