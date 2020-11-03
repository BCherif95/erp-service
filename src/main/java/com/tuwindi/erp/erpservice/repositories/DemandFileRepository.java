package com.tuwindi.erp.erpservice.repositories;

import com.tuwindi.erp.erpservice.entities.DemandFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandFileRepository extends JpaRepository<DemandFile, Long> {
    List<DemandFile> findAllByDemandId(Long demandId);
}
