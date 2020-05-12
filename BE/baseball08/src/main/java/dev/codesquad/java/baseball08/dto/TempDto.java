package dev.codesquad.java.baseball08.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class TempDto {
    private String name;
    private String userId;
    private Integer totalBat;
    private Integer totalHit;
    private Integer totalOut;
    private List<PlayersDto> players;

    @Builder
    public TempDto(String name, String userId, Integer totalBat, Integer totalHit, Integer totalOut, List<PlayersDto> players) {
        this.name = name;
        this.userId = userId;
        this.totalBat = totalBat;
        this.totalHit = totalHit;
        this.totalOut = totalOut;
        this.players = players;
    }
}
