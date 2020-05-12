package dev.codesquad.java.baseball08.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TotalDto {
    private Integer totalAtBat;
    private Integer totalHit;
    private Integer totalOutCount;

    public TotalDto() {}

    @Builder
    public TotalDto(Integer totalAtBat, Integer totalHit, Integer totalOutCount) {
        this.totalAtBat = totalAtBat;
        this.totalHit = totalHit;
        this.totalOutCount = totalOutCount;
    }
}
