package dev.codesquad.java.baseball08.entity;

import dev.codesquad.java.baseball08.entity.henry.History;
import lombok.Builder;
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
    private Integer currentInning;
    private String turn;

    private List<Inning> innings;
    private List<Team> teams;
    private List<History> histories;

    @Builder
    public Game(boolean onGame, Integer homeTotalScore, Integer awayTotalScore, Integer currentInning, String turn, List<Inning> innings, List<Team> teams) {
        this.onGame = onGame;
        this.homeTotalScore = homeTotalScore;
        this.awayTotalScore = awayTotalScore;
        this.currentInning = currentInning;
        this.turn = turn;
        this.innings = innings;
        this.teams = teams;
    }

    public void addInning(Inning inning) {
        this.innings.add(inning);
    }

    public void addTeam(Team team) {
        this.teams.add(team);
    }

    public void addHistory(History history) {
        this.histories.add(history);
    }
}
