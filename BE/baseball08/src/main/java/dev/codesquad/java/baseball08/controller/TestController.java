package dev.codesquad.java.baseball08.controller;

import dev.codesquad.java.baseball08.dto.ResponsePlayersDto;
import dev.codesquad.java.baseball08.dto.TempDto;
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
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    private final TeamService teamService;

    @GetMapping("/")
    public ResponseEntity<List<ResponsePlayersDto>>
    test() {
        return new ResponseEntity<>(teamService.teamPlayerInfo(1L), HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test2() {
        teamService.teamPlayerInfo(1L);
        return "ok";
    }


}
