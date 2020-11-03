package com.tuwindi.erp.erpservice.repositories;

import com.tuwindi.erp.erpservice.entities.Demand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandRepository extends JpaRepository<Demand, Long> {
}
