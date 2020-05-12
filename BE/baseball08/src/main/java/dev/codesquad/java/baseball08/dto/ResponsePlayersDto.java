package dev.codesquad.java.baseball08.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponsePlayersDto {
    private String team;
    private String user;
    private List<PlayersDto> players;
    private TotalDto total;

    public ResponsePlayersDto() {}

    @Builder
    public ResponsePlayersDto(String team, String user, List<PlayersDto> players, TotalDto total) {
        this.team = team;
        this.user = user;
        this.players = players;
        this.total = total;
    }

    public ResponsePlayersDto(PlayerInfoDto playerInfoDto, PlayersDto playersDto, TotalDto totalDto) {
        this.team = playerInfoDto.getTeam();
        this.user = playerInfoDto.getUser();
        this.players = playersDto.createList(playerInfoDto);
        this.total = totalDto;
    }
}
