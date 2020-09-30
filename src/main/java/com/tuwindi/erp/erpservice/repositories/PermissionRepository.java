package com.tuwindi.erp.erpservice.repositories;

import com.tuwindi.erp.erpservice.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
