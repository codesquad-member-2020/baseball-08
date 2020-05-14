package dev.codesquad.java.baseball08.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@ToString
public class Team {
    @Id
    private Long id;

    private String userId;
    private String name;
    private String pitcher;
    private String hitter;

    private List<Player> players;
    private List<History> histories;
}
