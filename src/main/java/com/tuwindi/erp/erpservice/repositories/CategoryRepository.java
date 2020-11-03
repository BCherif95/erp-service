package com.tuwindi.erp.erpservice.repositories;

import com.tuwindi.erp.erpservice.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);

    boolean existsDistinctByNameAndId(String name, Long id);
}
