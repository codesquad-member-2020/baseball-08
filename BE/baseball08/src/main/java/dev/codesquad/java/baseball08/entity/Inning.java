package dev.codesquad.java.baseball08.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class Inning {
    @Id
    private Long id;

    private Integer homeScore;
    private Integer awayScore;
    //    record
    private Integer strikeCount;
    private Integer ballCount;
    private Integer outCount;
    private Integer baseCount;
}
