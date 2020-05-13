package dev.codesquad.java.baseball08.dto.dto;

import lombok.Getter;

@Getter
public class AvailableDto {
    private boolean available;

    public AvailableDto(boolean available) {
        this.available = available;
    }
}
