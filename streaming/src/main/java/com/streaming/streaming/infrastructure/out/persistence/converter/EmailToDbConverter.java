package com.streaming.streaming.infrastructure.out.persistence.converter;

import com.streaming.streaming.domain.vo.Email;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class EmailToDbConverter implements Converter<Email, String> {
    @Override
    public String convert(Email source) {
        return source.value();
    }
}

