package com.raf.usermanagmentsystem.loader;

import com.raf.usermanagmentsystem.model.Privilege;
import com.raf.usermanagmentsystem.model.User;
import com.raf.usermanagmentsystem.repository.PrivilegeRepository;
import com.raf.usermanagmentsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PrivilegeRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public DataLoader(UserRepository userRepository,PrivilegeRepository privilegeRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading data...");

        //Privileges
        Privilege canReadUsers = createPrivilege("can_read_users");
        Privilege canCreateUsers = createPrivilege("can_create_users");
        Privilege canUpdateUsers = createPrivilege("can_update_users");
        Privilege canDeleteUsers = createPrivilege("can_delete_users");
        //vacuum privileges
        Privilege canSearchVacuum = createPrivilege("can_search_vacuum");
        Privilege canStartVacuum = createPrivilege("can_start_vacuum");
        Privilege canStopVacuum = createPrivilege("can_stop_vacuum");
        Privilege canDischargeVacuum = createPrivilege("can_discharge_vacuum");
        Privilege canAddVacuum = createPrivilege("can_add_vacuum");
        Privilege canRemoveVacuum = createPrivilege("can_remove_vacuum");

        //Users with privileges
        User user1 = createUser("user1@gmail.com", "User1", "Useric1", "12345");
        User user2 = createUser("user2@gmail.com", "User2", "Useric2", "12345");
        User user3 = createUser("user3@gmail.com", "User3", "Useric3", "12345");

        //Adding privileges to users
        addPrivilegesToUser(Arrays.asList(canReadUsers), user1);
        addPrivilegesToUser(Arrays.asList(canReadUsers, canCreateUsers, canUpdateUsers), user2);
        addPrivilegesToUser(Arrays.asList(canReadUsers, canCreateUsers, canUpdateUsers, canDeleteUsers), user3);

        System.out.println("Finished loading data.");
    }

    private Privilege createPrivilege(String name){
        Privilege privilege = new Privilege();
        privilege.setName(name);
        return this.privilegeRepository.save(privilege);
    }

    private User createUser(String email, String firstName, String lastName, String password){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(this.passwordEncoder.encode(password));
        return this.userRepository.save(user);
    }

    private void addPrivilegesToUser(List<Privilege> privileges, User user){
        for (Privilege privilege: privileges){
            user.getPrivileges().add(privilege);
        }
        userRepository.save(user);
    }
}
