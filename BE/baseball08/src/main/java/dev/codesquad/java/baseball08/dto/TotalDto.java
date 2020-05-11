package dev.codesquad.java.baseball08.dto;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class TotalDto {

    private Integer totalAtBat;
    private Integer totalHit;
    private Integer totalOutCount;

    public TotalDto() {
    }

    public static class Builder {

        private Integer totalAtBat;
        private Integer totalHit;
        private Integer totalOutCount;

        public Builder totalAtBat(int val) {
            totalAtBat = val;
            return this;
        }

        public Builder totalHit(int val) {
            totalHit = val;
            return this;
        }

        public Builder totalOutCount(int val) {
            totalOutCount = val;
            return this;
        }

        public TotalDto build() {
//            Assert.isNull(totalAtBat, "totalAtBat must not be null");
//            Assert.isNull(totalHit, "totalHit must not be null");
//            Assert.isNull(totalOutCount, "totlaOutCount must not be null");
            return new TotalDto(this);
        }
    }

    private TotalDto(Builder builder) {
        totalAtBat = builder.totalAtBat;
        totalHit = builder.totalHit;
        totalOutCount = builder.totalOutCount;
    }
}
