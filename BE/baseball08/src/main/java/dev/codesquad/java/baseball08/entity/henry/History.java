package dev.codesquad.java.baseball08.entity.henry;

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

    @Builder
    public History(String name, Integer lineUp, List<String> hitLog) {
        this.name = name;
        this.lineUp = lineUp;
        this.hitLog = hitLog;
    }
}
