package com.takebook.demo.dto;

import com.takebook.demo.model.AccountType;
import com.takebook.demo.model.Role;
import com.takebook.demo.validation.OnCreateValidationGroup;
import com.takebook.demo.validation.OnUpdateValidationGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Null(groups = OnCreateValidationGroup.class)
    @NotNull(groups = OnUpdateValidationGroup.class, message = "User ID should be not null")
    private Long id;

    @NotBlank(groups = {OnUpdateValidationGroup.class, OnCreateValidationGroup.class}, message = "User`s first name should be not null")
    private String firstName;

    @NotBlank(groups = {OnUpdateValidationGroup.class, OnCreateValidationGroup.class}, message = "User`s last name should be not null")
    private String lastName;

    @NotBlank(groups = {OnUpdateValidationGroup.class, OnCreateValidationGroup.class}, message = "User`s last name should be not null")
    private String password;

    @Email
    private String email;

    @NotNull(groups = {OnUpdateValidationGroup.class, OnCreateValidationGroup.class}, message = "User`s role should be not null")
    private Role role;

    @NotNull(groups = {OnUpdateValidationGroup.class, OnCreateValidationGroup.class}, message = "AccountType should be not null")
    private AccountType accountType;
}
