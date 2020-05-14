package dev.codesquad.java.baseball08.dto.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PlayballDto {

    // 게임 화면의 왼쪽에 해당하는 데이터 (이닝의 진행에 필요한 데이터)
    //Game Entity
    private Long gameId;
    private Integer homeTotalScore;
    private Integer awayTotalScore;
    //Inning Entity
    private Long inningId;
    private boolean topBottom;
    private String homeName;
    private String awayName;
    private Integer homeScore;
    private Integer awayScore;
    private Integer strikeCount;
    private Integer ballCount;
    private Integer outCount;
    private Integer baseCount;
    // -------------------------------------------------------------

    @Builder
    public PlayballDto(Long gameId, Integer homeTotalScore, Integer awayTotalScore, Long inningId, boolean topBottom,
                       String homeName, String awayName, Integer homeScore, Integer awayScore, Integer strikeCount,
                       Integer ballCount, Integer outCount, Integer baseCount) {
        this.gameId = gameId;
        this.homeTotalScore = homeTotalScore;
        this.awayTotalScore = awayTotalScore;
        this.inningId = inningId;
        this.topBottom = topBottom;
        this.homeName = homeName;
        this.awayName = awayName;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
        this.outCount = outCount;
        this.baseCount = baseCount;
    }
}
