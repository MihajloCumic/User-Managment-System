package com.raf.usermanagmentsystem.services.imp;

import com.raf.usermanagmentsystem.dto.UserCreateDto;
import com.raf.usermanagmentsystem.exceptions.PrivilegeNotFoundException;
import com.raf.usermanagmentsystem.model.Privilege;
import com.raf.usermanagmentsystem.model.User;
import com.raf.usermanagmentsystem.repository.PrivilegeRepository;
import com.raf.usermanagmentsystem.repository.UserRepository;
import com.raf.usermanagmentsystem.services.UserManagmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User createUser(UserCreateDto userCreateDto) {
        User user = new User();
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(this.passwordEncoder.encode(userCreateDto.getPassword()));

        if (userCreateDto.getPrivileges() != null && !userCreateDto.getPrivileges().isEmpty()){
            Set<Privilege> privileges = new HashSet<>();
            for(String privilegeName: userCreateDto.getPrivileges()){
                Optional<Privilege> privilegeOptional = this.privilegeRepository.findByName(privilegeName);
//                if(privilegeOptional.isPresent()){
//                    privileges.add(privilegeOptional.get());
//                }
                Privilege privilege = privilegeOptional.orElseThrow(() -> new PrivilegeNotFoundException());
                privileges.add(privilege);
            }
            user.setPrivileges(privileges);
        }
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
