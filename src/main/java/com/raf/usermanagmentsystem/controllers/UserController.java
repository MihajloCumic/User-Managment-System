package com.raf.usermanagmentsystem.controllers;

import com.raf.usermanagmentsystem.model.User;
import com.raf.usermanagmentsystem.services.UserManagmentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserManagmentService userManagmentService;

    public UserController(UserManagmentService userManagmentService){
        this.userManagmentService = userManagmentService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers(){
        try{
            System.out.println("Get mappign");
            return ResponseEntity.ok(this.userManagmentService.getUsers());
        }catch (Exception e){
            return ResponseEntity.status(500).build();
        }

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(){
        return ResponseEntity.ok("Create user");
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(){
        return ResponseEntity.ok("Update user");
    }

    @DeleteMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(){
        return ResponseEntity.ok("Delete user");
    }





}
