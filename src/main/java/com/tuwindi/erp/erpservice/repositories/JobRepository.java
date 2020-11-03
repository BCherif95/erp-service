package com.tuwindi.erp.erpservice.repositories;

import com.tuwindi.erp.erpservice.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

    boolean existsByTitle(String title);

    boolean existsDistinctByTitleAndId(String title, Long id);

}
