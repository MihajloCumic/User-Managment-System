package com.raf.usermanagmentsystem.services;

import com.raf.usermanagmentsystem.model.Privilege;
import com.raf.usermanagmentsystem.model.User;
import com.raf.usermanagmentsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found."));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapPrivilegesToAuthorities(user.getPrivileges()));
    }

    private Collection<GrantedAuthority> mapPrivilegesToAuthorities(Set<Privilege> privileges){
        return privileges.stream().map(privilege -> new SimpleGrantedAuthority(privilege.getName())).collect(Collectors.toSet());
    }
}
