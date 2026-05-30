package com.streaming.streaming.domain.ports.in;

import com.streaming.streaming.domain.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IVideoServicePort {
    Mono<Page<Video>> findAll(Pageable pageable);
    Mono<Video> findById(UUID id);
}
