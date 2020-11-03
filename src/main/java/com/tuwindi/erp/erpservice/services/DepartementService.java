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

    public Page<Department> getAll(PageBody pageBody) {
        Sort sort = Sort.by(pageBody.getSortdirection(), pageBody.getSortBy());
        Pageable pageable = PageRequest.of(pageBody.getPageNumber(), pageBody.getPageSize(), sort);
        return departementRepository.findAll(pageable);
    }

    public ResponseBody findAll() {
        try {
            return ResponseBody.with(departementRepository.findAll(), "Liste des departements !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !");
        }
    }

    public ResponseBody getById(Long id) {
        try {
            Optional<Department> optionalDepartment = departementRepository.findById(id);
            return optionalDepartment
                    .map(value -> ResponseBody.with(value, "Recuperer avec succes!"))
                    .orElseGet(() -> ResponseBody.error("Une erreur est survenue!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!");
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
                boolean isExist = departementRepository.existsDistinctByNameAndId(department.getName(), department.getId());
                if (!isExist) {
                    return ResponseBody.error("Ce département existe déjà !");
                }
                return ResponseBody.with(departementRepository.save(department), "Modifier avec succes !");
            } else {
                return ResponseBody.error("Ce département n'existe pas!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!");
        }
    }
}
