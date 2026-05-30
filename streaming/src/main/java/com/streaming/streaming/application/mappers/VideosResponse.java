package com.streaming.streaming.application.mappers;

import com.streaming.streaming.domain.model.Video;

import java.util.List;

public record VideosResponse(
        List<Video> videos,
        Long totalElements,
        Integer totalPages
) {
}
