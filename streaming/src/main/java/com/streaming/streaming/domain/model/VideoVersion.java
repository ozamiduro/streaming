package com.streaming.streaming.domain.model;

import com.streaming.streaming.domain.enums.VideoVersionStatus;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record VideoVersion(
        UUID id,
        UUID videoId,
        Integer versionNumber,
        VideoVersionStatus status,
        String masterPlaylistUrl,
        Instant createdAt,
        List<VideoStream> streams
) {

}
