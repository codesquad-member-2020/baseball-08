package dev.codesquad.java.baseball08.dto;

import lombok.Builder;
import lombok.Getter;

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
}
