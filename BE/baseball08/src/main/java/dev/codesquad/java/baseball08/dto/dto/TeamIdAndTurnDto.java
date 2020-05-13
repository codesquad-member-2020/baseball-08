package dev.codesquad.java.baseball08.dto.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TeamIdAndTurnDto {
    private Long awayId;
    private Long homeId;
    private String turn;

    @Builder
    public TeamIdAndTurnDto(Long awayId, Long homeId, String turn) {
        this.awayId = awayId;
        this.homeId = homeId;
        this.turn = turn;
    }
}
