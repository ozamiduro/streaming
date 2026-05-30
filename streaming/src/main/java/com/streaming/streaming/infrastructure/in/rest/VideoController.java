package com.streaming.streaming.infrastructure.in.rest;

import com.streaming.streaming.domain.model.Video;
import com.streaming.streaming.domain.ports.in.IVideoServicePort;
import com.streaming.streaming.infrastructure.in.rest.dto.PaginationResponse;
import com.streaming.streaming.infrastructure.in.rest.dto.SuccessResponse;
import com.streaming.streaming.infrastructure.in.rest.mapper.ResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideoController {
    private final IVideoServicePort videoService;

    private final ResponseMapper mapper;

    @GetMapping
    public Mono<ResponseEntity<SuccessResponse<List<Video>>>> findAll(
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            Locale locale
    ) {
        return videoService
                .findAll(Pageable.ofSize(size).withPage(page))
                .map(pageResponse ->
                        mapper.success(
                                locale,
                                "success.video.list",
                                pageResponse.getContent(),
                                PaginationResponse.fromPage(pageResponse),
                                null
                        )
                )
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<SuccessResponse<Video>>> findById(@PathVariable UUID id, Locale locale) {
        return videoService
                .findById(id)
                .map(video -> mapper.success(locale, "success.video.found", video))
                .map(ResponseEntity::ok);
    }
}
