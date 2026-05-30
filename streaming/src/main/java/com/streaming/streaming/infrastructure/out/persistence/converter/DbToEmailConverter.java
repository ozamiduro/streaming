package com.streaming.streaming.infrastructure.out.persistence.converter;

import com.streaming.streaming.domain.vo.Email;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class DbToEmailConverter implements Converter<String, Email> {
    @Override
    public Email convert(String source) {
        return new Email(source); // Creamos el VO desde el string de DB
    }
}
