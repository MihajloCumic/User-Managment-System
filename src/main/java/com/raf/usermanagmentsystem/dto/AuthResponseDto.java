package com.raf.usermanagmentsystem.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String jwt;
    public AuthResponseDto(String jwt){
        this.jwt = jwt;
    }

}
