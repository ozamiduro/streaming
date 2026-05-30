package com.streaming.streaming.infrastructure.in.rest.dto;

import java.time.Instant;

public record SuccessResponse<T>(
        boolean success,
        Instant timestamp,
        String message,
        T data,
        PaginationResponse pagination,
        Object metadata
) {
    public static <T> SuccessResponse<T> of(String message, T data) {
        return new SuccessResponse<T>(true, Instant.now(), message, data, null, null);
    }

    public static <T> SuccessResponse<T> of(String message, T data, PaginationResponse pagination, Object metadata) {
        return new SuccessResponse<T>(true, Instant.now(), message, data, pagination, metadata);
    }
}
