package dev.codesquad.java.baseball08.dto.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TotalDto {
    private Integer bat;
    private Integer hit;
    private Integer out;

    public TotalDto() {}

    @Builder
    public TotalDto(Integer bat, Integer hit, Integer out) {
        this.bat = bat;
        this.hit = hit;
        this.out = out;
    }
}
