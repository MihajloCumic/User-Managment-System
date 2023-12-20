package com.raf.usermanagmentsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.util.Set;

@Data
public class UserUpdateDto {
    @NotNull
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<String> privileges;
}
