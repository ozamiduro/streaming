package com.streaming.streaming.infrastructure.out.persistence.mappers;

import com.streaming.streaming.domain.model.VideoStream;
import com.streaming.streaming.infrastructure.out.persistence.entity.VideoStreamEntity;

public class VideoStreamMapper {
    public static VideoStream toDomain(VideoStreamEntity entity) {
        return new VideoStream(entity.id(), entity.videoVersionId(), entity.resolution(), entity.bitrate(), entity.playlistUrl(), entity.createdAt());
    }

    public static VideoStreamEntity toEntity(VideoStream domain) {
        return new VideoStreamEntity(domain.id(), domain.videoVersionId(), domain.resolution(), domain.bitrate(), domain.playlistUrl(), domain.createdAt());
    }
}
