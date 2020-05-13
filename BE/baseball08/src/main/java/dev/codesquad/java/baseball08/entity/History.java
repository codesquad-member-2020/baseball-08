package dev.codesquad.java.baseball08.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@ToString
public class History {
    @Id
    private Long id;

    private String name;
    private Integer lineUp;
    private List<String> hitLog;
    private Long game;
    private int gameKey;

    @Builder
    public History(String name, Integer lineUp, List<String> hitLog, Long game, int gameKey) {
        this.name = name;
        this.lineUp = lineUp;
        this.hitLog = hitLog;
        this.game = game;
        this.gameKey = gameKey;
    }
}
