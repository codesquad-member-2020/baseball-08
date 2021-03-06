package dev.codesquad.java.baseball08.dto.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
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
