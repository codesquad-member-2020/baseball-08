package dev.codesquad.java.baseball08.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GameListResponse {
    private Integer game;
    private Long awayId;
    private Long homeId;
    private String away;
    private String home;
    private String awayUser;
    private String homeUser;

    public GameListResponse() {}

    @Builder
    public GameListResponse(Integer game, String away, Long awayId, String home, Long homeId, String awayUser, String homeUser) {
        this.game = game;
        this.away = away;
        this.awayId = awayId;
        this.homeId = homeId;
        this.home = home;
        this.awayUser = awayUser;
        this.homeUser = homeUser;
    }
}
