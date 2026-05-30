package com.streaming.streaming.infrastructure.in.rest;

import com.streaming.streaming.domain.exception.NotFoundException;
import com.streaming.streaming.infrastructure.in.rest.dto.ErrorResponse;
import com.streaming.streaming.infrastructure.in.rest.mapper.ResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ResponseMapper mapper;

    @ExceptionHandler(NotFoundException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleNotFound(
            NotFoundException ex,
            Locale locale
    ) {
        return Mono.just(mapper
                        .failure(locale, ex.getMessage(), ex.getDetails()))
                .map(resp -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp));
    }

    @ExceptionHandler(ServerWebInputException.class)
    public Mono<ResponseEntity<ErrorResponse>> ServerWebInputException(
            MethodArgumentTypeMismatchException ex,
            Locale locale
    ) {
        return Mono.just(mapper
                        .failure(locale, ex.getMessage(), "error.bad-request"))
                .map(resp -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponse>> handleException(
            Exception ex,
            Locale locale
    ) {
        return Mono.just(mapper
                        .failure(locale, ex.getMessage(), "error.internal-server"))
                .map(resp -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp));
    }
}
