package dev.codesquad.java.baseball08.dto.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class PlayerInfoDto {
    private static final Logger logger = LoggerFactory.getLogger(PlayerInfoDto.class);

    private String team;
    private String user;
    private Integer totalAtBat;
    private Integer totalHit;
    private Integer totalOut;

    private List<String> names;
    private List<String> atBats;
    private List<String> hits;
    private List<String> outs;
    private List<String> averages;

    public PlayerInfoDto() {}

    @Builder
    public PlayerInfoDto(String team, String user, Integer totalAtBat, Integer totalHit, Integer totalOut, List<String> names, List<String> atBats, List<String> hits, List<String> outs, List<String> averages) {
        this.team = team;
        this.user = user;
        this.totalAtBat = totalAtBat;
        this.totalHit = totalHit;
        this.totalOut = totalOut;
        this.names = names;
        this.atBats = atBats;
        this.hits = hits;
        this.outs = outs;
        this.averages = averages;
    }

    public List<PlayersDto> createPlayers() {
        List<PlayersDto> players = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            players.add(PlayersDto.builder()
                    .name(names.get(i))
                    .atBat(Integer.parseInt(atBats.get(i)))
                    .hit(Integer.parseInt(hits.get(i)))
                    .out(Integer.parseInt(outs.get(i)))
                    .average(Double.parseDouble(averages.get(i)))
                    .build());
        }
        return players;
    }

    public TotalDto createTotal() {
        return TotalDto.builder()
                .bat(totalAtBat)
                .hit(totalHit)
                .out(totalOut)
                .build();
    }
}
