package com.streaming.streaming.domain.model;

import com.streaming.streaming.domain.vo.Email;

import java.time.Instant;
import java.util.UUID;

public record User(
        UUID id,
        String username,
        Email email,
        String passwordHash,
        Instant createdAt,
        Instant updatedAt
) {
}
