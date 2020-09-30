package com.tuwindi.erp.erpservice.services;

import com.tuwindi.erp.erpservice.entities.Category;
import com.tuwindi.erp.erpservice.repositories.CategoryRepository;
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
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Page<Category> getAll(PageBody pageBody) {
        Sort sort = Sort.by(pageBody.getSortdirection(), pageBody.getSortBy());
        Pageable pageable = PageRequest.of(pageBody.getPageNumber(), pageBody.getPageSize(), sort);
        return categoryRepository.findAll(pageable);
    }


    public ResponseBody findAll() {
        try {
            return ResponseBody.with(categoryRepository.findAll(), "Liste des catégories !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !");
        }
    }

    public ResponseBody getById(Long id) {
        try {
            Optional<Category> optionalCategory = categoryRepository.findById(id);
            return optionalCategory
                    .map(value -> ResponseBody.with(value, "Recuperer avec succes!"))
                    .orElseGet(() -> ResponseBody.error("Une erreur est survenue!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!");
        }
    }

    public ResponseBody create(Category category) {
        try {
            if (categoryRepository.existsByName(category.getName())) {
                return ResponseBody.error("Cette categorie existe déjà !!!");
            }
            return ResponseBody.with(categoryRepository.save(category), "Ajouter avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !!!");
        }
    }

    public ResponseBody update(Category category) {
        try {
            Optional<Category> categoryOptional = categoryRepository.findById(category.getId());
            if (categoryOptional.isPresent()) {
                boolean isExist = categoryRepository.findDistinctByIdAndName(category.getId(), category.getName()).isEmpty();
                if (!isExist) {
                    return ResponseBody.error("Cette categorie existe déjà !");
                }
                return ResponseBody.with(categoryRepository.save(category), "Modifier avec succes !");
            } else {
                return ResponseBody.error("Cet categorie n'existe pas!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!");
        }
    }
}
