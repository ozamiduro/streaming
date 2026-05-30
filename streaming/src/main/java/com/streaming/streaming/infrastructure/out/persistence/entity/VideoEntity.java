package com.streaming.streaming.infrastructure.out.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("videos")
public record VideoEntity(
        @Id UUID id,
        UUID userId,
        String title,
        String description,
        UUID currentVersionId,
        Instant createdAt
) {
}
