package com.tuwindi.erp.erpservice.repositories;

import com.tuwindi.erp.erpservice.entities.LinePartner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LinePartnerRepository extends JpaRepository<LinePartner, Long> {
    @Query(value = "SELECT line from LinePartner line  where line.budgetLine.budget.id =:idBudget")
    Page<LinePartner> findAllElementByBudget(@Param("idBudget") Long idBudget, Pageable pageable);
}
