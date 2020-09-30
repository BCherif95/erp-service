package com.tuwindi.erp.erpservice.services;

import com.tuwindi.erp.erpservice.entities.Employee;
import com.tuwindi.erp.erpservice.repositories.EmployeeRepository;
import com.tuwindi.erp.erpservice.utils.PageBody;
import com.tuwindi.erp.erpservice.utils.ResponseBody;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public ResponseBody getAll(PageBody pageBody) {
        try {
            Sort sort = Sort.by(pageBody.getSortdirection(), pageBody.getSortBy());
            Pageable pageable = PageRequest.of(pageBody.getPageNumber(), pageBody.getPageSize(), sort);
            Page<Employee> employees = employeeRepository.findAll(pageable);
            return ResponseBody.with(employees, "Liste des employés !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!!!");
        }
    }

    public ResponseBody create(Employee employee) {
        try {
            if (employeeRepository.existsByFirstnameAndLastname(employee.getFirstname(), employee.getLastname())) {
                return ResponseBody.error("Cet employé existe déjà !!!");
            }
            return ResponseBody.with(employeeRepository.save(employee), "Ajouter avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !!!");
        }
    }

    public ResponseBody update(Employee employee) {
        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());
            if (!employeeOptional.isPresent()) {
                return ResponseBody.error("Cet employé n'existe pas !!!");
            }
            return ResponseBody.with(create(employee), "Modifier avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !!!");
        }
    }
}
