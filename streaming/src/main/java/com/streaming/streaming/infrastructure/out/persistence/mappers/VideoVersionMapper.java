package com.streaming.streaming.infrastructure.out.persistence.mappers;

import com.streaming.streaming.domain.model.VideoStream;
import com.streaming.streaming.domain.model.VideoVersion;
import com.streaming.streaming.infrastructure.out.persistence.entity.VideoVersionEntity;

import java.util.List;

public class VideoVersionMapper {
    public static VideoVersion toDomain(VideoVersionEntity entity) {
        return new VideoVersion(entity.id(), entity.videoId(), entity.versionNumber(), entity.status(), entity.masterPlaylistUrl(), entity.createdAt(), null);
    }

    public static VideoVersion toDomain(VideoVersionEntity entity, List<VideoStream> streams) {
        return new VideoVersion(entity.id(), entity.videoId(), entity.versionNumber(), entity.status(), entity.masterPlaylistUrl(), entity.createdAt(), streams);
    }

    public static VideoVersionEntity toEntity(VideoVersion domain) {
        return new VideoVersionEntity(domain.id(), domain.videoId(), domain.versionNumber(), domain.status(), domain.masterPlaylistUrl(), domain.createdAt());
    }
}
