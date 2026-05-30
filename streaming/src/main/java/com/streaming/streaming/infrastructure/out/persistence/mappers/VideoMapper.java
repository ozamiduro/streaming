package com.streaming.streaming.infrastructure.out.persistence.mappers;

import com.streaming.streaming.domain.model.Category;
import com.streaming.streaming.domain.model.Video;
import com.streaming.streaming.domain.model.VideoVersion;
import com.streaming.streaming.infrastructure.out.persistence.entity.VideoEntity;

import java.util.List;

public class VideoMapper {
    public static Video toDomain(VideoEntity entity) {
        return new Video(entity.id(), entity.userId(), entity.title(), entity.description(), entity.currentVersionId(), entity.createdAt(), null, null);
    }

    public static Video toDomain(VideoEntity entity, VideoVersion currentVersion) {
        return new Video(entity.id(), entity.userId(), entity.title(), entity.description(), entity.currentVersionId(), entity.createdAt(), currentVersion, null);
    }

    public static Video toDomain(VideoEntity entity, List<Category> categories) {
        return new Video(entity.id(), entity.userId(), entity.title(), entity.description(), entity.currentVersionId(), entity.createdAt(), null, categories);
    }

    public static Video toDomain(VideoEntity entity, VideoVersion currentVersion, List<Category> categories) {
        return new Video(entity.id(), entity.userId(), entity.title(), entity.description(), entity.currentVersionId(), entity.createdAt(), currentVersion, categories);
    }

    public static VideoEntity toEntity(Video domain) {
        return new VideoEntity(domain.id(), domain.userId(), domain.title(), domain.description(), domain.currentVersionId(), domain.createdAt());
    }
}
