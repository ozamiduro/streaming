package com.streaming.streaming.infrastructure.out.persistence.repository;

import com.streaming.streaming.infrastructure.out.persistence.entity.VideoStreamEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface VideoStreamCrudRepository extends ReactiveCrudRepository<VideoStreamEntity, UUID> {
    Flux<VideoStreamEntity> findByVideoVersionId(UUID videoId);
}
