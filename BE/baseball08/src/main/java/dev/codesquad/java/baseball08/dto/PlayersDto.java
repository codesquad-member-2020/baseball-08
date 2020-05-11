package dev.codesquad.java.baseball08.dto;

import lombok.Getter;
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

        public Builder atBat(int val) {
            atBat = val;
            return this;
        }

        public Builder hit(int val) {
            hit = val;
            return this;
        }

        public Builder outCount(int val) {
            outCount = val;
            return this;
        }

        public Builder average(double val) {
            average = val;
            return this;
        }

        public PlayersDto build() {
            Assert.hasText(name, "PlayerName must not be null");
//            Assert.isNull(atBat, "Player atBat must not be null");
//            Assert.isNull(hit, "Player hit must not be null");
//            Assert.isNull(outCount, "Player outCount must not be null");
//            Assert.isNull(average, "Player average must not be null");
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
