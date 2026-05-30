package com.streaming.streaming.domain.model;

import java.time.Instant;
import java.util.UUID;

public record VideoStream(
        UUID id,
        UUID videoVersionId,
        String resolution,
        Integer bitrate,
        String playlistUrl,
        Instant createdAt
) {
}
