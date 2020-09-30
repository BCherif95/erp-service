package com.tuwindi.erp.erpservice.repositories;

import com.tuwindi.erp.erpservice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByName(String name);

    Role findByName(String name);
}
