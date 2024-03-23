package com.example.charityBook.dto;

import com.example.charityBook.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull(message = "User ID should be not null")
    private Long id;

    @NotBlank(message = "User`s first name should be not null")
    private String firstName;

    @NotBlank(message = "User`s last name should be not null")
    private String lastName;

    @NotBlank(message = "User`s last name should be not null")
    private String password;

    @Email
    private String email;

    @NotNull(message = "User`s role should be not null")
    private Role role;

}
