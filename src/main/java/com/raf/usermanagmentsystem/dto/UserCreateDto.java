package com.raf.usermanagmentsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class UserCreateDto {

    @NotBlank(message = "First name must be provided.")
    private String firstName;
    @NotBlank(message = "Last name must be provided.")
    private String lastName;
    @Email(message = "Incorrect form of an email.")
    private String email;
    @NotBlank(message = "Password must be provided.")
    private String password;

    private Set<String> privileges;
}
