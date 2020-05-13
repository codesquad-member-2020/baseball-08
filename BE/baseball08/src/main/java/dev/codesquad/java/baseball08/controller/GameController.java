package dev.codesquad.java.baseball08.controller;

import dev.codesquad.java.baseball08.dto.AvailableDto;
import dev.codesquad.java.baseball08.dto.StageDto;
import dev.codesquad.java.baseball08.service.GameService;
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
public class GameController {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);
    private final GameService gameService;

    @GetMapping("/game")
    public ResponseEntity<AvailableDto> isGamePossible() {
        return new ResponseEntity<>(gameService.isGamePossible(1L), HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<List<StageDto>> info() {
        return new ResponseEntity<>(gameService.getGameInfo(),HttpStatus.OK);
    }

    @GetMapping("/save/history")
    public void saveHistory() {
        gameService.saveHistory();
    }

    @GetMapping("/init/inning")
    public void initInning() {
        gameService.saveNewInning();
    }
}
