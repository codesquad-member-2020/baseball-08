package dev.codesquad.java.baseball08.controller;

import dev.codesquad.java.baseball08.dto.dto.AvailableDto;
import dev.codesquad.java.baseball08.dto.response.GameListResponse;
import dev.codesquad.java.baseball08.dto.response.PlayersResponse;
import dev.codesquad.java.baseball08.dto.response.AvailabilityResponse;
import dev.codesquad.java.baseball08.service.GameService;
import dev.codesquad.java.baseball08.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
    private final TeamService teamService;
    private final GameService gameService;

    // 게임의 선수 정보를 불러오는 API
    @GetMapping("/detail/{gameId}/player")
    public ResponseEntity<List<PlayersResponse>> test(@PathVariable("gameId") Long gameId) {
        return new ResponseEntity<>(teamService.teamPlayerInfo(gameId), HttpStatus.OK);
    }
    // team 선택 가능 여부 판단하는 API
    @GetMapping("/team/{teamName}")
    public ResponseEntity<AvailabilityResponse> isTeamAvailable(@PathVariable("teamName") String teamName) {
        return new ResponseEntity<>(teamService.isTeamAvailable(teamName), HttpStatus.OK);
    }

    @GetMapping("/henry")
    public ResponseEntity test2() {
//        return new ResponseEntity(teamService.getTeamPlayersInfo(1L), HttpStatus.OK);
//        return new ResponseEntity(gameService.isGameAvailable(2L), HttpStatus.OK);
//        return new ResponseEntity(teamService.isTeamAvailable(1L) ,HttpStatus.OK);
//        return ResponseEntity.ok(teamService.getTeamScore(1L));
//        return ResponseEntity.ok(gameService.getGameList());
//        return ResponseEntity.ok(teamService.getPlayerLog(1L));
        return ResponseEntity.ok(gameService.getGamePlay(1L, 1L));
    }

}
