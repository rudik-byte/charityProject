package com.example.charityBook.dto.mapper;

import com.example.charityBook.dto.AuthorDto;
import com.example.charityBook.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper extends GenericMapper<Author, AuthorDto> {
}
