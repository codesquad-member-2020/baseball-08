package dev.codesquad.java.baseball08.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class Inning {
    @Id
    private Long id;

    private String homeName;
    private String awayName;

    private boolean topBottom;

    private Integer homeScore;
    private Integer awayScore;
    //    record
    private Integer strikeCount;
    private Integer ballCount;
    private Integer outCount;
    private Integer baseCount;
    private Long game;
    private Integer game_key;

    @Builder
    public Inning(String homeName, String awayName, boolean topBottom, Integer homeScore, Integer awayScore, Integer strikeCount,
                  Integer ballCount, Integer outCount, Integer baseCount, Long game, Integer game_key) {
        this.homeName = homeName;
        this.awayName = awayName;
        this.topBottom = topBottom;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
        this.outCount = outCount;
        this.baseCount = baseCount;
        this.game = game;
        this.game_key = game_key;
    }
}
