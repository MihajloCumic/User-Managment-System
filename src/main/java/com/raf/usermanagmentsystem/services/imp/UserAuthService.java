package com.raf.usermanagmentsystem.services.imp;

import com.raf.usermanagmentsystem.dto.AuthResponseDto;
import com.raf.usermanagmentsystem.dto.LoginDto;
import com.raf.usermanagmentsystem.services.AuthService;
import com.raf.usermanagmentsystem.services.UserService;
import com.raf.usermanagmentsystem.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserAuthService implements AuthService<LoginDto, AuthResponseDto> {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserAuthService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Optional<AuthResponseDto> authenticate(LoginDto loginDto) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        List<String> privileges = authentication.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toList());
        return Optional.of(new AuthResponseDto(jwtUtil.generateToken(loginDto.getEmail(), privileges)));
    }
}
