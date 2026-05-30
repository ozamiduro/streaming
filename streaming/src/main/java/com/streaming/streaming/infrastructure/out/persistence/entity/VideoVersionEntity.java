package com.streaming.streaming.infrastructure.out.persistence.entity;

import com.streaming.streaming.domain.enums.VideoVersionStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("video_versions")
public record VideoVersionEntity(
        @Id UUID id,
        UUID videoId,
        Integer versionNumber,
        VideoVersionStatus status,
        String masterPlaylistUrl,
        Instant createdAt
) {
}
