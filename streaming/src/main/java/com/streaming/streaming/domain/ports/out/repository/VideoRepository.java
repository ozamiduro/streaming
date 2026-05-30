package com.streaming.streaming.domain.ports.out.repository;

import com.streaming.streaming.domain.model.Video;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface VideoRepository {
    Mono<Long> count();
    Flux<Video> findAll(Pageable pageable);
    Mono<Video> findById(UUID id);
    Mono<Void> save(Video video);
}
