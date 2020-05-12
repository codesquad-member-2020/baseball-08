package dev.codesquad.java.baseball08.entity;

import dev.codesquad.java.baseball08.entity.henry.History;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@ToString
public class Game {
    @Id
    private Long id;

    private boolean onGame;
    private Integer homeTotalScore;
    private Integer awayTotalScore;

    private List<Inning> innings;
    private List<Team> teams;
    private List<History> histories;

    public Game(boolean onGame, Integer homeTotalScore, Integer awayTotalScore, List<Inning> innings, List<Team> teams, List<History> histories) {
        this.onGame = onGame;
        this.homeTotalScore = homeTotalScore;
        this.awayTotalScore = awayTotalScore;
        this.innings = innings;
        this.teams = teams;
        this.histories = histories;
    }
}
