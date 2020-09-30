package com.tuwindi.erp.erpservice.repositories;

import com.tuwindi.erp.erpservice.entities.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    boolean existsByName(String name);

    List<Partner> findDistinctByIdAndName(Long id, String name);
}
