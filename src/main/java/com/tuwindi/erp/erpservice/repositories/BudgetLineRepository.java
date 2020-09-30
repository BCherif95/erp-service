package com.tuwindi.erp.erpservice.repositories;

import com.tuwindi.erp.erpservice.entities.BudgetLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetLineRepository extends JpaRepository<BudgetLine, Long> {
    List<BudgetLine> findAllByBudgetId(Long budgetId);

    BudgetLine findByBudgetId(Long budgetId);

    BudgetLine findBudgetLineById(Long id);

    Page<BudgetLine> findAllByBudgetId(Long id, Pageable pageable);
}
