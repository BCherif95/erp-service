package com.tuwindi.erp.erpservice.services;

import com.tuwindi.erp.erpservice.entities.Demand;
import com.tuwindi.erp.erpservice.repositories.DemandRepository;
import com.tuwindi.erp.erpservice.utils.Enumeration;
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
public class DemandService {

    private final DemandRepository demandRepository;

    public Page<Demand> getAll(PageBody pageBody) {
        Sort sort = Sort.by(pageBody.getSortdirection(), pageBody.getSortBy());
        Pageable pageable = PageRequest.of(pageBody.getPageNumber(), pageBody.getPageSize(), sort);
        return demandRepository.findAll(pageable);
    }

    public ResponseBody findAll() {
        try {
            return ResponseBody.with(demandRepository.findAll(), "Liste des demandes !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !");
        }
    }

    public ResponseBody getById(Long id) {
        try {
            Optional<Demand> optionalDemand = demandRepository.findById(id);
            return optionalDemand
                    .map(value -> ResponseBody.with(value, "Recuperer avec succes!"))
                    .orElseGet(() -> ResponseBody.error("Une erreur est survenue!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!");
        }
    }

    public ResponseBody create(Demand demand) {
        try {
            return ResponseBody.with(demandRepository.save(demand), "Ajouter avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !!!");
        }
    }

    public ResponseBody update(Demand demand) {
        try {
            Optional<Demand> demandOptional = demandRepository.findById(demand.getId());
            if (!demandOptional.isPresent()) {
                return ResponseBody.error("Cette entité n'existe pas !!!");
            }
            return ResponseBody.with(demandRepository.save(demand), "Modifier avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !!!");
        }
    }

    public ResponseBody approved(Demand demand) {
        try {
            Optional<Demand> demandOptional = demandRepository.findById(demand.getId());
            if (!demandOptional.isPresent()) {
                return ResponseBody.error("Cet objet n'existe pas !!!");
            }
            Demand newDemand = demandOptional.get();
            newDemand.setDemandState(Enumeration.DEMAND_STATE.IN_VALIDATION);
            return ResponseBody.with(demandRepository.save(newDemand), "Approuver avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !!!");
        }
    }

    public ResponseBody validate(Demand demand) {
        try {
            Optional<Demand> demandOptional = demandRepository.findById(demand.getId());
            if (!demandOptional.isPresent()) {
                return ResponseBody.error("Cet objet n'existe pas !!!");
            }
            Demand newDemand = demandOptional.get();
            newDemand.setDemandState(Enumeration.DEMAND_STATE.TO_CONFIRM);
            return ResponseBody.with(demandRepository.save(newDemand), "Valider avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !!!");
        }
    }
}
