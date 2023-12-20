package com.raf.usermanagmentsystem.services;

import com.raf.usermanagmentsystem.dto.UserCreateDto;
import com.raf.usermanagmentsystem.dto.UserUpdateDto;
import com.raf.usermanagmentsystem.model.User;

import java.util.List;

public interface UserManagmentService {
    User getUserById(Long id);
    List<User> getUsers();
    User createUser(UserCreateDto userCreateDto);
    User updateUser(UserUpdateDto userUpdateDto, Long id);

    User deleteUser(Long userId);

}
