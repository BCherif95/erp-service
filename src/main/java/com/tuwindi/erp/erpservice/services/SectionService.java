package com.tuwindi.erp.erpservice.services;

import com.tuwindi.erp.erpservice.entities.Section;
import com.tuwindi.erp.erpservice.entities.Unity;
import com.tuwindi.erp.erpservice.repositories.SectionRepository;
import com.tuwindi.erp.erpservice.repositories.UnitRepository;
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
public class SectionService {

    private final SectionRepository sectionRepository;

    public ResponseBody getAll(PageBody pageBody) {
        try {
            Sort sort = Sort.by(pageBody.getSortdirection(), pageBody.getSortBy());
            Pageable pageable = PageRequest.of(pageBody.getPageNumber(), pageBody.getPageSize(), sort);
            Page<Section> sections = sectionRepository.findAll(pageable);
            return ResponseBody.with(sections, "Liste des sections !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!!!");
        }
    }

    public ResponseBody create(Section section) {
        try {
            if (sectionRepository.existsByTitle(section.getTitle())) {
                return ResponseBody.error("Cette section existe déjà !!!");
            }
            return ResponseBody.with(sectionRepository.save(section), "Ajouter avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !!!");
        }
    }

    public ResponseBody update(Section section) {
        try {
            Optional<Section> sectionOptional = sectionRepository.findById(section.getId());
            if (sectionOptional.isPresent()) {
                boolean isExist = sectionRepository.existsDistinctByTitleAndId(section.getTitle(),section.getId());
                if (!isExist) {
                    return ResponseBody.error("Cette section existe déjà !");
                }
                return ResponseBody.with(sectionRepository.save(section), "Modifier avec succes !");
            } else {
                return ResponseBody.error("Cette section n'existe pas!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!");
        }
    }
}
