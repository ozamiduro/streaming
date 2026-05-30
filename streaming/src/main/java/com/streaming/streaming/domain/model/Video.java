package com.streaming.streaming.domain.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record Video(
        UUID id,
        UUID userId,
        String title,
        String description,
        UUID currentVersionId,
        Instant createdAt,
        VideoVersion currentVersion,
        List<Category> categories
) {
}
