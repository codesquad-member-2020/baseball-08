package dev.codesquad.java.baseball08.dto.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ScoreDto {
    private Integer strike;
    private Integer ball;
    private Integer out;
    private Integer base;

    public ScoreDto() {}

    @Builder
    public ScoreDto(Integer strike, Integer ball, Integer out, Integer base) {
        this.strike = strike;
        this.ball = ball;
        this.out = out;
        this.base = base;
    }
}
