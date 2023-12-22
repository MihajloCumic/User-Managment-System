package com.raf.usermanagmentsystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class PrivilegeResponseDto {
    private List<String> privileges;
}
