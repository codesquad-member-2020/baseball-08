package dev.codesquad.java.baseball08.controller;

import dev.codesquad.java.baseball08.dto.response.PlayersResponse;
import dev.codesquad.java.baseball08.dto.response.AvailabilityResponse;
import dev.codesquad.java.baseball08.service.GameService;
import dev.codesquad.java.baseball08.service.TeamService;
import dev.codesquad.java.baseball08.service.henry.PitchServiceHenry;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
    private final TeamService teamService;
    private final PitchServiceHenry pitchService;

    // 게임의 선수 정보를 불러오는 API
    @GetMapping("/detail/{gameId}/player")
    public ResponseEntity<List<PlayersResponse>> getPlayerInfoByGameId(@PathVariable("gameId") Long gameId) {
        return new ResponseEntity<>(teamService.teamPlayerInfo(gameId), HttpStatus.OK);
    }
    // team 선택 가능 여부 판단 및 팀을 선택하는 API
    @GetMapping("/team/{teamId}")
    public ResponseEntity<AvailabilityResponse> isTeamAvailable(@RequestHeader(value = "userId") String userId, @PathVariable("teamId") Long teamId) {
        if (userId != null) {
            return new ResponseEntity<>(teamService.isTeamAvailable(teamId, userId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 미사용 // team 선택 하는 API
    @GetMapping("/team/{teamId}/select")
    public ResponseEntity<HttpStatus> selectTeam(@RequestHeader(value = "userId") String userId, @PathVariable("teamId") Long teamId) {
        if (userId != null) {
            teamService.updateUserId(teamId, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
