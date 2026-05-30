package com.streaming.streaming.application.services;

import com.streaming.streaming.domain.model.Video;
import com.streaming.streaming.domain.ports.in.IVideoServicePort;
import com.streaming.streaming.domain.ports.out.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoService implements IVideoServicePort {
    private final VideoRepository videoRepository;

    @Override
    public Mono<Page<Video>> findAll(Pageable pageable) {
        Mono<Long> count = videoRepository.count();
        Flux<Video> videos = videoRepository.findAll(pageable);

        return Mono
                .zip(count, videos.collectList())
                .map(tuple -> new PageImpl<>(tuple.getT2(), pageable, tuple.getT1()));
    }

    @Override
    public Mono<Video> findById(UUID id) {
        return videoRepository.findById(id);
    }
}
