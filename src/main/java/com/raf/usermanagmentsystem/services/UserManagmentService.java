package com.raf.usermanagmentsystem.services;

import com.raf.usermanagmentsystem.dto.UserCreateDto;
import com.raf.usermanagmentsystem.model.User;

import java.util.List;

public interface UserManagmentService {
    List<User> getUsers();
    User createUser(UserCreateDto userCreateDto);

    void deleteUser(Long userId);

}
