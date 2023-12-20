package com.raf.usermanagmentsystem.services;

import com.raf.usermanagmentsystem.dto.UserCreateDto;
import com.raf.usermanagmentsystem.dto.UserUpdateDto;
import com.raf.usermanagmentsystem.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserManagmentService {
    User getUserById(Long id);
    List<User> getUsers();
    List<User> getUsers(Pageable pageable);
    User createUser(UserCreateDto userCreateDto);
    User updateUser(UserUpdateDto userUpdateDto, Long id);

    User deleteUser(Long userId);

}
