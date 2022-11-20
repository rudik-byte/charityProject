package com.takebook.demo.dto.mapper;

import com.takebook.demo.dto.AuthorDto;
import com.takebook.demo.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper extends GenericMapper<Author, AuthorDto>{
}
