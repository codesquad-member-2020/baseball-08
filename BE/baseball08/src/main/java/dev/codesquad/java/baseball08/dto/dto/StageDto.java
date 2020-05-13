package dev.codesquad.java.baseball08.dto.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StageDto {
    private Integer game;
    private String away;
    private String home;
    private String awayUser;
    private String homeUser;

    @Builder
    public StageDto(Integer game, String away, String home, String awayUser, String homeUser) {
        this.game = game;
        this.away = away;
        this.home = home;
        this.awayUser = awayUser;
        this.homeUser = homeUser;
    }
}
