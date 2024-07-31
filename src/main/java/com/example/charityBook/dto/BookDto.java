package com.example.charityBook.dto;

import com.example.charityBook.model.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    @NotNull(message = "Book ID should be not null")
    private Long id;

//    @NotBlank(message = "Authors should be not null")
    private Set<AuthorDto> authors;

    @NotBlank(message = "Book title should be not null")
    private String name;

    @NotBlank(message = "Language should be not null")
    private String language;

    @NotNull(message = "Book genre should be not null")
    private Genre genre;

    @NotNull(message = "Book price should be not null")
    private double price;
}
