package com.tuwindi.erp.erpservice.repositories;

import com.tuwindi.erp.erpservice.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsByTitle(String title);

    List<Project> findDistinctByIdAndTitle(Long id, String title);
}
