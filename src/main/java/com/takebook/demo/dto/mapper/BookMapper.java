package com.takebook.demo.dto.mapper;

import com.takebook.demo.dto.BookDto;
import com.takebook.demo.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper extends GenericMapper<Book, BookDto>{
}
