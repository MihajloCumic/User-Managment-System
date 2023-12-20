package com.raf.usermanagmentsystem.controllers;

import com.raf.usermanagmentsystem.dto.UserCreateDto;
import com.raf.usermanagmentsystem.dto.UserUpdateDto;
import com.raf.usermanagmentsystem.model.User;
import com.raf.usermanagmentsystem.services.UserManagmentService;
import jakarta.validation.Valid;
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

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(this.userManagmentService.getUserById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers(){
        try{
            return ResponseEntity.ok(this.userManagmentService.getUsers());
        }catch (Exception e){
            return ResponseEntity.status(500).build();
        }

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateDto userCreateDto){
        return ResponseEntity.ok(this.userManagmentService.createUser(userCreateDto));
    }

    @PatchMapping (value = "{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto, @PathVariable Long id){
        return ResponseEntity.ok(this.userManagmentService.updateUser(userUpdateDto, id));
    }

    @DeleteMapping (value = "{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok(this.userManagmentService.deleteUser(id));
    }





}
