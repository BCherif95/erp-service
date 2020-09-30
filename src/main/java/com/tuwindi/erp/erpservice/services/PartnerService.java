package com.tuwindi.erp.erpservice.services;

import com.tuwindi.erp.erpservice.entities.Category;
import com.tuwindi.erp.erpservice.entities.Partner;
import com.tuwindi.erp.erpservice.repositories.PartnerRepository;
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
public class PartnerService {

    private final PartnerRepository partnerRepository;

    public Page<Partner> getAll(PageBody pageBody) {
        Sort sort = Sort.by(pageBody.getSortdirection(), pageBody.getSortBy());
        Pageable pageable = PageRequest.of(pageBody.getPageNumber(), pageBody.getPageSize(), sort);
        return partnerRepository.findAll(pageable);
    }

    public ResponseBody findAll() {
        try {
            return ResponseBody.with(partnerRepository.findAll(), "Liste de partenaires !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !");
        }
    }

    public ResponseBody getById(Long id) {
        try {
            Optional<Partner> optionalPartner = partnerRepository.findById(id);
            return optionalPartner
                    .map(value -> ResponseBody.with(value, "Recuperer avec succes!"))
                    .orElseGet(() -> ResponseBody.error("Une erreur est survenue!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!");
        }
    }

    public ResponseBody create(Partner partner) {
        try {
            if (partnerRepository.existsByName(partner.getName())) {
                return ResponseBody.error("Ce partenaire existe déjà !!!");
            }
            return ResponseBody.with(partnerRepository.save(partner), "Ajouter avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !!!");
        }
    }

    public ResponseBody update(Partner partner) {
        try {
            Optional<Partner> partnerOptional = partnerRepository.findById(partner.getId());
            if (partnerOptional.isPresent()) {
                boolean isExist = partnerRepository.findDistinctByIdAndName(partner.getId(), partner.getName()).isEmpty();
                if (!isExist) {
                    return ResponseBody.error("Ce partenaire existe déjà !");
                }
                return ResponseBody.with(partnerRepository.save(partner), "Modifier avec succes !");
            } else {
                return ResponseBody.error("Cet partenaire n'existe pas!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!");
        }
    }
}
