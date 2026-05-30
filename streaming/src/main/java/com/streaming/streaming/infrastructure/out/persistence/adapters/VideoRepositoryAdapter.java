package com.streaming.streaming.infrastructure.out.persistence.adapters;

import com.streaming.streaming.domain.exception.NotFoundException;
import com.streaming.streaming.domain.model.Category;
import com.streaming.streaming.domain.model.Video;
import com.streaming.streaming.domain.model.VideoStream;
import com.streaming.streaming.domain.model.VideoVersion;
import com.streaming.streaming.domain.ports.out.repository.VideoRepository;
import com.streaming.streaming.infrastructure.out.persistence.entity.VideoEntity;
import com.streaming.streaming.infrastructure.out.persistence.entity.VideoVersionEntity;
import com.streaming.streaming.infrastructure.out.persistence.mappers.VideoMapper;
import com.streaming.streaming.infrastructure.out.persistence.mappers.VideoStreamMapper;
import com.streaming.streaming.infrastructure.out.persistence.mappers.VideoVersionMapper;
import com.streaming.streaming.infrastructure.out.persistence.queries.VideoCategoryQueryRepository;
import com.streaming.streaming.infrastructure.out.persistence.repository.VideoCrudRepository;
import com.streaming.streaming.infrastructure.out.persistence.repository.VideoStreamCrudRepository;
import com.streaming.streaming.infrastructure.out.persistence.repository.VideoVersionCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class VideoRepositoryAdapter implements VideoRepository {
    private final VideoCrudRepository videoCrudRepository;
    private final VideoCategoryQueryRepository videoCategoryQueryRepository;
    private final VideoVersionCrudRepository videoVersionCrudRepository;
    private final VideoStreamCrudRepository videoStreamCrudRepository;

    private Mono<Video> buildVideoAggregate(VideoEntity videoEntity) {
        Mono<VideoVersionEntity> versionEntityMono = videoVersionCrudRepository
                .findById(videoEntity.currentVersionId())
                .switchIfEmpty(Mono.error(new NotFoundException("error.video.not-found.version")));

        Mono<List<VideoStream>> streamEntityMono = videoStreamCrudRepository
                .findByVideoVersionId(videoEntity.currentVersionId())
                .map(VideoStreamMapper::toDomain)
                .collectList();

        Mono<List<Category>> categoriesMono = videoCategoryQueryRepository
                .findCategoriesByVideoId(videoEntity.id())
                .collectList();

        return Mono.zip(
                        versionEntityMono,
                        streamEntityMono,
                        categoriesMono
                )
                .map(tuple -> {
                    VideoVersion version = VideoVersionMapper.toDomain(tuple.getT1(), tuple.getT2());
                    return VideoMapper.toDomain(videoEntity, version, tuple.getT3());
                });
    }

    @Override
    public Mono<Long> count() {
        return videoCrudRepository.count();
    }

    @Override
    public Flux<Video> findAll(Pageable pageable) {
        return videoCrudRepository
                .findAllBy(pageable)
                .map(VideoMapper::toDomain);
    }

    @Override
    public Mono<Video> findById(UUID id) {
        return videoCrudRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("error.video.not-found")))
                .flatMap(this::buildVideoAggregate);
    }

    @Override
    public Mono<Void> save(Video video) {
        return null;
    }
}
