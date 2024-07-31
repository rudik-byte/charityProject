package com.example.charityBook.convertor;

import com.example.charityBook.exception.NotFoundException;
import com.example.charityBook.model.Language;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class LanguageConvertor implements AttributeConverter<Language, String> {

    @Override
    public String convertToDatabaseColumn(Language language) {
        if (language == null) {
            return null;
        }
        return language.getName();
    }

    @Override
    public Language convertToEntityAttribute(String name) {
        if (name == null) {
            return null;
        }

        return Stream.of(Language.values())
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(name));
    }
}
