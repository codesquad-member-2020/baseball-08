package dev.codesquad.java.baseball08.dto.henry;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class TeamScoreResponse {
    private String team;
    private List<Integer> score = new ArrayList<>();
    private Integer totalScore;
    private String user;
    private boolean turn;

    public TeamScoreResponse() {}

    @Builder
    public TeamScoreResponse(String team, List<Integer> score, Integer totalScore, String user, boolean turn) {
        this.team = team;
        this.score = score;
        this.totalScore = totalScore;
        this.user = user;
        this.turn = turn;
    }
}
