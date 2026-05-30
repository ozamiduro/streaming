package com.streaming.streaming.infrastructure.in.rest.dto;

import org.springframework.data.domain.Page;

public record PaginationResponse(
        Integer page,
        Integer size,
        Long totalElements,
        Integer totalPage
) {
    public static PaginationResponse of(Integer page, Integer size) {
        return new PaginationResponse(page, size, null, null);
    }

    public static PaginationResponse of(Integer page, Integer size, Long totalElements, Integer totalPage) {
        return new PaginationResponse(page, size, totalElements, totalPage);
    }

    public static <T> PaginationResponse fromPage(Page<T> page) {
        return new PaginationResponse(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
