package com.streaming.streaming.infrastructure.out.persistence.entity;

import com.streaming.streaming.domain.vo.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("users")
public record UserEntity(
        @Id UUID id,
        String username,
        Email email,
        String passwordHash,
        Instant createdAt,
        Instant updatedAt
) {
}
