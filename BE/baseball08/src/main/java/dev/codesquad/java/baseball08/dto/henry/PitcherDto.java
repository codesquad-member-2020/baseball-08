package dev.codesquad.java.baseball08.dto.henry;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PitcherDto {
    private String name;
    private Integer pitches;

    public PitcherDto() {}

    @Builder
    public PitcherDto(String name, Integer pitches) {
        this.name = name;
        this.pitches = pitches;
    }
}
