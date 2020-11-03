package com.tuwindi.erp.erpservice.repositories;

import com.tuwindi.erp.erpservice.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {
    boolean existsByTitle(String title);

    boolean existsDistinctByTitleAndId(String title, Long id);
}
