package dev.codesquad.java.baseball08.controller;

import dev.codesquad.java.baseball08.dto.AvailableDto;
import dev.codesquad.java.baseball08.dto.ResponsePlayersDto;
import dev.codesquad.java.baseball08.dto.henry.AvailabilityResponse;
import dev.codesquad.java.baseball08.service.GameService;
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
    private final GameService gameService;

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
//        return new ResponseEntity(teamService.getTeamPlayersInfo(1L), HttpStatus.OK);
//        return new ResponseEntity(gameService.isGameAvailable(2L), HttpStatus.OK);
//        return new ResponseEntity(teamService.isTeamAvailable(1L) ,HttpStatus.OK);
//        return ResponseEntity.ok(teamService.getTeamScore(1L));
//        return ResponseEntity.ok(gameService.getGameList());
//        return ResponseEntity.ok(teamService.getPlayerLog(1L));
        return ResponseEntity.ok(gameService.getGamePlay(1L,1L));
    }

    @GetMapping("/test")
    public ResponseEntity<AvailableDto> test3() {
        return new ResponseEntity<>(teamService.isTeamAvailable2(1L, 2L), HttpStatus.OK);
    }

    @GetMapping("/test/henry")
    public ResponseEntity<AvailabilityResponse> test4() {
        return new ResponseEntity<>(teamService.isTeamAvailable(1L), HttpStatus.OK);
    }

}
