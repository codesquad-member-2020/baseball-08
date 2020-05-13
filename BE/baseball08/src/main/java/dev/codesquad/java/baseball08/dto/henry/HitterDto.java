package dev.codesquad.java.baseball08.dto.henry;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HitterDto {
    private String name;
    private Integer atBat;
    private Integer hit;

    public HitterDto() {}

    @Builder
    public HitterDto(String name, Integer atBat, Integer hit) {
        this.name = name;
        this.atBat = atBat;
        this.hit = hit;
    }
}
