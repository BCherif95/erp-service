package com.tuwindi.erp.erpservice.repositories;

import com.tuwindi.erp.erpservice.entities.Unity;
import com.tuwindi.erp.erpservice.utils.Enumeration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unity, Long> {
    boolean existsByTitle(String title);

    boolean existsDistinctByTitleAndId(String title, Long id);

    List<Unity> findAllByTypeUnity(Enumeration.TYPE_UNITY typeUnity);
}
