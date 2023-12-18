package com.raf.usermanagmentsystem.services;

import java.util.Optional;

public interface AuthService <RequestDto, ResponseDto>{
    Optional<ResponseDto> authenticate(RequestDto requestDto);
}
