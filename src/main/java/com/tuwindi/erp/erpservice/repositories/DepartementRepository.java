package com.tuwindi.erp.erpservice.repositories;

import com.tuwindi.erp.erpservice.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartementRepository extends JpaRepository<Department, Long> {
    boolean existsByName(String name);

    boolean existsDistinctByNameAndId(String name, Long id);
}
