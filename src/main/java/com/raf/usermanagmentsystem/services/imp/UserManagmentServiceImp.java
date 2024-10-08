package com.raf.usermanagmentsystem.services.imp;

import com.raf.usermanagmentsystem.dto.UserCreateDto;
import com.raf.usermanagmentsystem.dto.UserUpdateDto;
import com.raf.usermanagmentsystem.exceptions.PrivilegeNotFoundException;
import com.raf.usermanagmentsystem.model.Privilege;
import com.raf.usermanagmentsystem.model.User;
import com.raf.usermanagmentsystem.repository.PrivilegeRepository;
import com.raf.usermanagmentsystem.repository.UserRepository;
import com.raf.usermanagmentsystem.services.UserManagmentService;
import jakarta.transaction.TransactionalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.util.*;

@Service
public class UserManagmentServiceImp implements UserManagmentService {
    private final UserRepository userRepository;
    private final PrivilegeRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserManagmentServiceImp(UserRepository userRepository,PrivilegeRepository privilegeRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public User getUserById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User does not exists"));
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User does not exists"));
        return user;
    }

    @Override
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public List<User> getUsers(Pageable pageable) {
        return this.userRepository.findAll(pageable).getContent();
    }

    @Override
    public User createUser(UserCreateDto userCreateDto) {
        User user = new User();
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(this.passwordEncoder.encode(userCreateDto.getPassword()));

        if (userCreateDto.getPrivileges() != null && !userCreateDto.getPrivileges().isEmpty()){
            Set<Privilege> privileges = this.mapPrivileges(userCreateDto.getPrivileges());
            user.setPrivileges(privileges);
        }
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(UserUpdateDto userUpdateDto, String email) {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
        if (userUpdateDto.getFirstName() != null){
            user.setFirstName(userUpdateDto.getFirstName());
        }
        if(userUpdateDto.getLastName() != null){
            user.setLastName(userUpdateDto.getLastName());
        }
        if(userUpdateDto.getEmail() != null){
            user.setEmail(userUpdateDto.getEmail());
        }
        if(userUpdateDto.getPassword() != null){
            user.setPassword(this.passwordEncoder.encode(userUpdateDto.getPassword()));
        }
        if(userUpdateDto.getPrivileges() != null){
            user.setPrivileges(this.mapPrivileges(userUpdateDto.getPrivileges()));
        }
        return this.userRepository.save(user);
    }

    @Override
    public User deleteUser(String email) {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
        user.getPrivileges().clear();
        this.userRepository.delete(user);
        return user;

    }

    private Set<Privilege> mapPrivileges(Set<String> privilegeNames){
        Set<Privilege> privileges = new HashSet<>();
        for(String privilegeName: privilegeNames){
            Optional<Privilege> privilegeOptional = this.privilegeRepository.findByName(privilegeName);
            Privilege privilege = privilegeOptional.orElseThrow(() -> new PrivilegeNotFoundException());
            privileges.add(privilege);
        }
        return privileges;
    }
}
