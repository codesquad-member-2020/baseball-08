package dev.codesquad.java.baseball08.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("player_log")
@Getter
@Setter
@ToString
public class Log {
    @Id
    private Long id;

}
