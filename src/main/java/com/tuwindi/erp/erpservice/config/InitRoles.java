package com.tuwindi.erp.erpservice.config;

import com.tuwindi.erp.erpservice.entities.Role;
import com.tuwindi.erp.erpservice.repositories.RoleRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class InitRoles implements ApplicationRunner {

    private final RoleRepository roleRepository;

    public InitRoles(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Role roleAdmin = roleRepository.findByName("ADMIN");
        if (roleAdmin == null) {
            roleAdmin = new Role("ADMIN");
            roleRepository.save(roleAdmin);
        }

    }
}
