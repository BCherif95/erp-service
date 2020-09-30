package com.tuwindi.erp.erpservice.services;

import com.tuwindi.erp.erpservice.entities.Department;
import com.tuwindi.erp.erpservice.repositories.DepartementRepository;
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
public class DepartementService {

    private final DepartementRepository departementRepository;

    public ResponseBody getAll(PageBody pageBody) {
        try {
            Sort sort = Sort.by(pageBody.getSortdirection(), pageBody.getSortBy());
            Pageable pageable = PageRequest.of(pageBody.getPageNumber(), pageBody.getPageSize(), sort);
            Page<Department> departments = departementRepository.findAll(pageable);
            return ResponseBody.with(departments, "Liste des départements !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!!!");
        }
    }

    public ResponseBody create(Department department) {
        try {
            if (departementRepository.existsByName(department.getName())) {
                return ResponseBody.error("Ce department existe déjà !!!");
            }
            return ResponseBody.with(departementRepository.save(department), "Ajouter avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !!!");
        }
    }

    public ResponseBody update(Department department) {
        try {
            Optional<Department> departmentOptional = departementRepository.findById(department.getId());
            if (departmentOptional.isPresent()) {
                boolean isExist = departementRepository.findDistinctByIdAndName(department.getId(), department.getName()).isEmpty();
                if (!isExist) {
                    return ResponseBody.error("Ce département existe déjà !");
                }
                return ResponseBody.with(departementRepository.save(department), "Modifier avec succes !");
            } else {
                return ResponseBody.error("Cet département n'existe pas!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!");
        }
    }
}
