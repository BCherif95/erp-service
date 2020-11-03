package com.tuwindi.erp.erpservice.repositories;

import com.tuwindi.erp.erpservice.entities.Spend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpendRepository extends JpaRepository<Spend, Long> {
    List<Spend> findAllByBudgetLineId(Long id);

    Spend findSpendById(Long id);
}
