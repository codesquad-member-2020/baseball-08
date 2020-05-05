package dev.codesquad.java.baseball08.entity;

import org.springframework.data.annotation.Id;

public class Player {
    @Id
    private Long id;

    private String name;
    private Integer lineUp;
//    record
    private Integer atBat;
    private Integer hit;
    private Integer out;
    private Integer pitches;
    private Double average;
}
