package com.streaming.streaming.application.services;

import com.streaming.streaming.domain.ports.in.IMessageServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageServicePort {
    private final MessageSource messageSource;

    @Override
    public String get(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }

    @Override
    public String get(String key, Object[] args, Locale locale) {
        return messageSource.getMessage(key, args, locale);
    }
}
