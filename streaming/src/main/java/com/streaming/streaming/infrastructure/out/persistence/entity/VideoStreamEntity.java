package com.streaming.streaming.infrastructure.out.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("video_streams")
public record VideoStreamEntity(
        @Id UUID id,
        UUID videoVersionId,
        String resolution,
        Integer bitrate,
        String playlistUrl,
        Instant createdAt
) {
}
