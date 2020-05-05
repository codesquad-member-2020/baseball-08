package dev.codesquad.java.baseball08.entity;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Team {
    @Id
    private Long id;

    private String name;
    private String currentPlayer;
    private List<Player> players;
}
