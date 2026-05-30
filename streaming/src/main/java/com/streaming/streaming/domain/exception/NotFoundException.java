package com.streaming.streaming.domain.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final String details;

    public NotFoundException(String message) {
        super(message);
        this.details = null;
    }

    public NotFoundException(String message, String details) {
        super(message);
        this.details = details;
    }
}
