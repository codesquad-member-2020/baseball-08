package dev.codesquad.java.baseball08.dto;

import dev.codesquad.java.baseball08.oauth.RequestBody;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Getter
public class PlayersDto {

    private String name;
    private Integer atBat;
    private Integer hit;
    private Integer outCount;
    private Double average;

    public PlayersDto() {
    }

    public static class Builder {
        private String name;
        private Integer atBat;
        private Integer hit;
        private Integer outCount;
        private Double average;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder atBat(Integer val) {
            atBat = val;
            return this;
        }

        public Builder hit(Integer val) {
            hit = val;
            return this;
        }

        public Builder outCount(Integer val) {
            outCount = val;
            return this;
        }

        public Builder average(Double val) {
            average = val;
            return this;
        }

        public PlayersDto build() {
            Assert.hasText(name, "PlayerName must not be null");
            Assert.isNull(atBat, "Player atBat must not be null");
            Assert.isNull(hit, "Player hit must not be null");
            Assert.isNull(outCount, "Player outCount must not be null");
            Assert.isNull(average, "Player average must not be null");
            return new PlayersDto(this);
        }
    }

    private PlayersDto(Builder builder) {
        name = builder.name;
        atBat = builder.atBat;
        hit = builder.hit;
        outCount = builder.outCount;
        average = builder.average;
    }
}
