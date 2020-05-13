package dev.codesquad.java.baseball08.dto.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PlayerLogDto {
    private String name;
    private Integer lineUp;
    private List<String> hitLog;

    public PlayerLogDto() {}

    @Builder
    public PlayerLogDto(String name, Integer lineUp, List<String> hitLog) {
        this.name = name;
        this.lineUp = lineUp;
        this.hitLog = hitLog;
    }
}
