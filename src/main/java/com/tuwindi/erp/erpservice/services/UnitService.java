package com.tuwindi.erp.erpservice.services;

import com.tuwindi.erp.erpservice.entities.Unity;
import com.tuwindi.erp.erpservice.repositories.UnitRepository;
import com.tuwindi.erp.erpservice.utils.PageBody;
import com.tuwindi.erp.erpservice.utils.ResponseBody;
import com.tuwindi.erp.erpservice.utils.SearchBody;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;

    public Page<Unity> getAll(PageBody pageBody) {
        Sort sort = Sort.by(pageBody.getSortdirection(), pageBody.getSortBy());
        Pageable pageable = PageRequest.of(pageBody.getPageNumber(), pageBody.getPageSize(), sort);
        return unitRepository.findAll(pageable);
    }

    public ResponseBody findAll() {
        try {
            return ResponseBody.with(unitRepository.findAll(), "Liste des unités !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !");
        }
    }

    public ResponseBody getById(Long id) {
        try {
            Optional<Unity> optionalUnity = unitRepository.findById(id);
            return optionalUnity
                    .map(value -> ResponseBody.with(value, "Recuperer avec succes!"))
                    .orElseGet(() -> ResponseBody.error("Une erreur est survenue!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!");
        }
    }

    public ResponseBody getAllByType(SearchBody searchBody) {
        try {
            return ResponseBody.with(unitRepository.findAllByTypeUnity(searchBody.getTypeUnity()), "Liste d'unité");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !");
        }
    }

    public ResponseBody create(Unity unity) {
        try {
            if (unitRepository.existsByTitle(unity.getTitle())) {
                return ResponseBody.error("Cette unité existe déjà !!!");
            }
            return ResponseBody.with(unitRepository.save(unity), "Ajouter avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !!!");
        }
    }

    public ResponseBody update(Unity unity) {
        try {
            Optional<Unity> unityOptional = unitRepository.findById(unity.getId());
            if (unityOptional.isPresent()) {
                boolean isExist = unitRepository.existsDistinctByTitleAndId(unity.getTitle(), unity.getId());
                if (!isExist) {
                    return ResponseBody.error("Cette unité existe déjà !");
                }
                return ResponseBody.with(unitRepository.save(unity), "Modifier avec succes !");
            } else {
                return ResponseBody.error("Cette unité n'existe pas!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!");
        }
    }
}
