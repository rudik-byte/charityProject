package com.example.charityBook.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    @NotNull
    private Long id;

    @NotNull(message = "Author`s name should be not null")
    private String name;
}
