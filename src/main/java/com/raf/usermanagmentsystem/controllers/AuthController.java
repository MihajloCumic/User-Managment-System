package com.raf.usermanagmentsystem.controllers;

import com.raf.usermanagmentsystem.dto.AuthResponseDto;
import com.raf.usermanagmentsystem.dto.LoginDto;
import com.raf.usermanagmentsystem.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        Optional<AuthResponseDto> optionalAuthResponseDto = authService.authenticate(loginDto);
        if(optionalAuthResponseDto.isPresent()){
            return ResponseEntity.ok(optionalAuthResponseDto.get());
        }
        return ResponseEntity.status(401).build();
    }
}
