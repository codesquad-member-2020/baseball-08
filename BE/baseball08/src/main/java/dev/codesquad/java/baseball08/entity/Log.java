package dev.codesquad.java.baseball08.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class Log {
    @Id
    private Long id;

    private String hitLog;

    @Builder
    public Log(String hitLog) {
        this.hitLog = hitLog;
    }
}
