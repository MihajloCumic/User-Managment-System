package com.raf.usermanagmentsystem.loader;

import com.raf.usermanagmentsystem.model.User;
import com.raf.usermanagmentsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading data...");
        User user = new User();
        user.setFirstName("Mihajlo");
        user.setLastName("Cumic");
        user.setEmail("email@.com");
        user.setPassword(passwordEncoder.encode("123"));
        userRepository.save(user);
    }
}
