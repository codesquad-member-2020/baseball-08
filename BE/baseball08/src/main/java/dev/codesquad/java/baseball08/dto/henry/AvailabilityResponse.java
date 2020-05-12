package dev.codesquad.java.baseball08.dto.henry;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AvailabilityResponse {
    private boolean available;

    public AvailabilityResponse() {}

    @Builder
    public AvailabilityResponse(boolean available) {
        this.available = available;
    }
}
