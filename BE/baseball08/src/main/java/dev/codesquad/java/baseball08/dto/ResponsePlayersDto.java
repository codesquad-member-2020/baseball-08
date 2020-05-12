package dev.codesquad.java.baseball08.dto;
;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.List;

@Getter
public class ResponsePlayersDto {

    private final String team;
    private final String user;
    private final List<PlayersDto> players;
    private final TotalDto total;

    @Builder
    public ResponsePlayersDto(String team, String user, List<PlayersDto> teamPlayers, TotalDto totalDto) {
        this.team = team;
        this.user = user;
        this.players = teamPlayers;
        this.total = totalDto;
    }
}
