package com.streaming.streaming.infrastructure.in.rest.dto;

import java.time.Instant;

public record ErrorResponse(
        boolean success,
        Instant timestamp,
        String message,
        String details
) {
    public static ErrorResponse of(String message, String details) {
        return new ErrorResponse(false, Instant.now(), message, details);
    }
}
