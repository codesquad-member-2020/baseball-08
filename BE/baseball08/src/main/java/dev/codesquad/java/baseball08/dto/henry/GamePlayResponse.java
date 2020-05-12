package dev.codesquad.java.baseball08.dto.henry;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GamePlayResponse {
    private String away;
    private String home;
    private Integer awayTotalScore;
    private Integer homeTotalScore;
    private String user;
    private Integer inning;
    private String turn; // 초, 말 응답으로 변경
    private ScoreDto score;
    private PitcherDto pitcher;
    private HitterDto hitter;

}
