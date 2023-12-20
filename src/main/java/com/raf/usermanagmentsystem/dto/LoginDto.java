package com.raf.usermanagmentsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank(message = "Email must be provided.")
    @Email(message = "Incorrect form of an email.")
    private String email;
    @NotBlank(message = "Password must be provided.")
    private String password;
}
