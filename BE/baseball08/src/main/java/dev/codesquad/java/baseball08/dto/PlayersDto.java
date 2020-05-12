package dev.codesquad.java.baseball08.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PlayersDto {
    private String name;
    private Integer atBat;
    private Integer hit;
    private Integer out;
    private Double average;

    public PlayersDto() {}

    @Builder
    public PlayersDto(String name, Integer atBat, Integer hit, Integer out, Double average) {
        this.name = name;
        this.atBat = atBat;
        this.hit = hit;
        this.out = out;
        this.average = average;
    }

    public List<PlayersDto> createList(PlayerInfoDto playerInfoDto) {
        List<PlayersDto> players = new ArrayList<>();
        for (int i = 0; i < playerInfoDto.getNames().size(); i++) {
            players.add(PlayersDto.builder()
                    .name(playerInfoDto.getNames().get(i))
                    .atBat(Integer.parseInt(playerInfoDto.getAtBats().get(i)))
                    .hit(Integer.parseInt(playerInfoDto.getHits().get(i)))
                    .out(Integer.parseInt(playerInfoDto.getOuts().get(i)))
                    .average(Double.parseDouble(playerInfoDto.getAverages().get(i)))
                    .build());
        }
        return players;
    }
}
