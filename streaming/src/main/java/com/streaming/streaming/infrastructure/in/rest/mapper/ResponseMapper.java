package com.streaming.streaming.infrastructure.in.rest.mapper;

import com.streaming.streaming.domain.ports.in.IMessageServicePort;
import com.streaming.streaming.infrastructure.in.rest.dto.ErrorResponse;
import com.streaming.streaming.infrastructure.in.rest.dto.PaginationResponse;
import com.streaming.streaming.infrastructure.in.rest.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class ResponseMapper {
    private final IMessageServicePort messageService;

    public <T> SuccessResponse<T> success(Locale locale, String messageKey, T data) {
        String message = messageService.get(messageKey, locale);
        return SuccessResponse.of(message, data, null, null);
    }

    public <T> SuccessResponse<T> success(Locale locale, String messageKey, T data, PaginationResponse paginationResponse, Object metadata) {
        String message = messageService.get(messageKey, locale);
        return SuccessResponse.of(message, data, paginationResponse, metadata);
    }

    public ErrorResponse failure(Locale locale, String messageKey, String details) {
        String message = messageService.get(messageKey, locale);
        return ErrorResponse.of(message, details);
    }
}
