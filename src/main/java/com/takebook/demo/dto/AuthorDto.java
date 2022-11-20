package com.takebook.demo.dto;

import com.takebook.demo.validation.OnCreateValidationGroup;
import com.takebook.demo.validation.OnUpdateValidationGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    @Null(groups = OnCreateValidationGroup.class)
    @NotNull(groups = OnUpdateValidationGroup.class)
    private Long id;

    @NotNull(groups = {OnUpdateValidationGroup.class, OnCreateValidationGroup.class}, message = "Author`s name should be not null")
    private String name;
}
