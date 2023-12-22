package com.raf.usermanagmentsystem.controllers;

import com.raf.usermanagmentsystem.dto.UserCreateDto;
import com.raf.usermanagmentsystem.dto.UserUpdateDto;
import com.raf.usermanagmentsystem.model.User;
import com.raf.usermanagmentsystem.services.UserManagmentService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserManagmentService userManagmentService;

    public UserController(UserManagmentService userManagmentService){
        this.userManagmentService = userManagmentService;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(this.userManagmentService.getUserById(id));
    }

    @GetMapping(value = "email",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByEmail(@RequestParam(value = "email",required = true)String email){
        return ResponseEntity.ok(this.userManagmentService.getUserByEmail(email));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(value = "pageNumber",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",required = false) Integer pageSize
    ){
        try{
            if(pageNumber == null || pageSize == null) return ResponseEntity.ok(this.userManagmentService.getUsers());
            return ResponseEntity.ok(this.userManagmentService.getUsers(PageRequest.of(pageNumber, pageSize)));
        }catch (Exception e){
            return ResponseEntity.status(500).build();
        }

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateDto userCreateDto){
            return ResponseEntity.ok(this.userManagmentService.createUser(userCreateDto));
    }

    @PatchMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto, @RequestParam(value = "email",required = true) String email){
            return ResponseEntity.ok(this.userManagmentService.updateUser(userUpdateDto, email));
    }

    @DeleteMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@RequestParam(value = "email",required = true) String email){
        return ResponseEntity.ok(this.userManagmentService.deleteUser(email));
    }





}
