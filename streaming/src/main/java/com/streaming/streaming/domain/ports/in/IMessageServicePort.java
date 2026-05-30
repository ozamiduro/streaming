package com.streaming.streaming.domain.ports.in;

import java.util.Locale;

public interface IMessageServicePort {
    String get(String key, Locale locale);

    String get(String key, Object[] args, Locale locale);
}
