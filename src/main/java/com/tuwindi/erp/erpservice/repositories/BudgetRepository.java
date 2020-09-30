package com.tuwindi.erp.erpservice.repositories;

import com.tuwindi.erp.erpservice.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    boolean existsByTitle(String title);
}
