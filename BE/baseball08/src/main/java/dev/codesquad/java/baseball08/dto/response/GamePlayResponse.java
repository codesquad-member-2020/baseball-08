package dev.codesquad.java.baseball08.dto.response;

import dev.codesquad.java.baseball08.dto.dto.HitterDto;
import dev.codesquad.java.baseball08.dto.dto.PitcherDto;
import dev.codesquad.java.baseball08.dto.dto.PlayerLogDto;
import dev.codesquad.java.baseball08.dto.dto.ScoreDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class GamePlayResponse {
    private String away;
    private String home;
    private Integer awayTotalScore;
    private Integer homeTotalScore;
    private String user;
    private Integer inning;
    private String turn;
    private ScoreDto score;
    private PitcherDto pitcher;
    private HitterDto hitter;
    private List<PlayerLogDto> history = new ArrayList<>();

    public GamePlayResponse() {}

    @Builder
    public GamePlayResponse(String away, String home, Integer awayTotalScore, Integer homeTotalScore, String user, Integer inning, String turn, ScoreDto score, PitcherDto pitcher, HitterDto hitter, List<PlayerLogDto> history) {
        this.away = away;
        this.home = home;
        this.awayTotalScore = awayTotalScore;
        this.homeTotalScore = homeTotalScore;
        this.user = user;
        this.inning = inning;
        this.turn = turn;
        this.score = score;
        this.pitcher = pitcher;
        this.hitter = hitter;
        this.history = history;
    }
}
