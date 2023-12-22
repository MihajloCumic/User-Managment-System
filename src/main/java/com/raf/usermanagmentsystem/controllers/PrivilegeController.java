package com.raf.usermanagmentsystem.controllers;


import com.raf.usermanagmentsystem.dto.PrivilegeResponseDto;
import com.raf.usermanagmentsystem.model.User;
import com.raf.usermanagmentsystem.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/privileges")
@CrossOrigin
public class PrivilegeController {
    private final PrivilegeRepository privilegeRepository;
    @Autowired
    public PrivilegeController(PrivilegeRepository privilegeRepository){
        this.privilegeRepository = privilegeRepository;
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PrivilegeResponseDto> getPrivileges(){
        try{
            PrivilegeResponseDto privilegeResponseDto = new PrivilegeResponseDto();
            List<String> privileges = this.privilegeRepository.findAll().stream().map(privilege -> privilege.getName()).collect(Collectors.toList());
            privilegeResponseDto.setPrivileges(privileges);
            return ResponseEntity.ok(privilegeResponseDto);
        }catch (Exception e){
            return ResponseEntity.status(500).build();
        }
    }

}
