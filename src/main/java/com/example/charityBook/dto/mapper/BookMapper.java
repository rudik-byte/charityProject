package com.example.charityBook.dto.mapper;

import com.example.charityBook.dto.BookDto;
import com.example.charityBook.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper extends GenericMapper<Book, BookDto> {
}
