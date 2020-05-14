package dev.codesquad.java.baseball08.dto.response;

import dev.codesquad.java.baseball08.dto.dto.PlayerInfoDto;
import dev.codesquad.java.baseball08.dto.dto.PlayersDto;
import dev.codesquad.java.baseball08.dto.dto.TotalDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PlayersResponse {
    private String team;
    private String user;
    private List<PlayersDto> players;
    private TotalDto total;

    public PlayersResponse() {}

    @Builder
    public PlayersResponse(String team, String user, List<PlayersDto> players, TotalDto total) {
        this.team = team;
        this.user = user;
        this.players = players;
        this.total = total;
    }

    public PlayersResponse(PlayerInfoDto playerInfoDto) {
        this.team = playerInfoDto.getTeam();
        this.user = playerInfoDto.getUser();
        this.players = playerInfoDto.createPlayers();
        this.total = playerInfoDto.createTotal();
    }
}
