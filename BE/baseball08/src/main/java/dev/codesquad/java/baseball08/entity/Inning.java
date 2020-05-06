package dev.codesquad.java.baseball08.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

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
