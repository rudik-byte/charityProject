package com.takebook.demo.dto;

import com.takebook.demo.model.Author;
import com.takebook.demo.model.Genre;
import com.takebook.demo.validation.OnCreateValidationGroup;
import com.takebook.demo.validation.OnUpdateValidationGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    @Null(groups = OnCreateValidationGroup.class)
    @NotNull(groups = OnUpdateValidationGroup.class, message = "Book ID should be not null")
    private Long id;

    @NotBlank(groups = {OnUpdateValidationGroup.class, OnCreateValidationGroup.class},message = "Book ID should be not null")
    private Set<AuthorDto> authors;

    @NotBlank(groups = {OnUpdateValidationGroup.class, OnCreateValidationGroup.class}, message = "Book title should be not null")
    private String name;

    @NotBlank(groups = {OnUpdateValidationGroup.class, OnCreateValidationGroup.class},message = "Publisher should be not null")
    private String publisher;

    @NotNull(groups = {OnUpdateValidationGroup.class, OnCreateValidationGroup.class},message = "Book genre should be not null")
    private Genre genre;

    @NotBlank(groups = {OnUpdateValidationGroup.class, OnCreateValidationGroup.class},message = "Book ISBN should be not null")
    private String isbn;
}
