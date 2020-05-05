package dev.codesquad.java.baseball08.entity;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Game {
    @Id
    private Long id;

    private boolean onGame;
    private Integer homeTotalScore;
    private Integer awayTotalScore;

    private List<Inning> innings;
    private List<Team> teams;
}
