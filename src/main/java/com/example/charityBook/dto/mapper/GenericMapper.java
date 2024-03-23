package com.example.charityBook.dto.mapper;


import org.mapstruct.MappingTarget;

public interface GenericMapper<T,F> {

    T fromDto(F dto);

    F toDto(T model);

    void update(T model, @MappingTarget F dto);
}
