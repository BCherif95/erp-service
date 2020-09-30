package com.tuwindi.erp.erpservice.repositories;

import com.tuwindi.erp.erpservice.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByFirstnameAndLastname(String firstname, String lastname);
}
