package com.tuwindi.erp.erpservice.config;

import com.tuwindi.erp.erpservice.entities.Role;
import com.tuwindi.erp.erpservice.entities.User;
import com.tuwindi.erp.erpservice.repositories.RoleRepository;
import com.tuwindi.erp.erpservice.repositories.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@Order(2)
public class InitUsers implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public InitUsers(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Role role = roleRepository.findByName("ADMIN");

        User userAdmin = userRepository.findByUsername("admin");
        if (userAdmin == null) {
            userAdmin = new User();
            userAdmin.setUsername("admin");
            userAdmin.setPassword(passwordEncoder.encode("admin"));
            userAdmin.setCreateDate(new Date());
            Set<Role> roles = new HashSet<>(Collections.singletonList(role));
            userAdmin.setRoles(roles);
            userRepository.save(userAdmin);
        }

    }
}
