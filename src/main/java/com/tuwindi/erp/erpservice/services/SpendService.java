package com.tuwindi.erp.erpservice.services;

import com.tuwindi.erp.erpservice.entities.BudgetLine;
import com.tuwindi.erp.erpservice.entities.Spend;
import com.tuwindi.erp.erpservice.repositories.BudgetLineRepository;
import com.tuwindi.erp.erpservice.repositories.SpendRepository;
import com.tuwindi.erp.erpservice.upload.FilesStorageService;
import com.tuwindi.erp.erpservice.utils.Enumeration;
import com.tuwindi.erp.erpservice.utils.PageBody;
import com.tuwindi.erp.erpservice.utils.ResponseBody;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SpendService {

    private final SpendRepository spendRepository;
    private final BudgetLineRepository budgetLineRepository;
    private final FilesStorageService storageService;

    public Page<Spend> getAll(PageBody pageBody) {
        Sort sort = Sort.by(pageBody.getSortdirection(), pageBody.getSortBy());
        Pageable pageable = PageRequest.of(pageBody.getPageNumber(), pageBody.getPageSize(), sort);
        return spendRepository.findAll(pageable);
    }

    public ResponseBody disbursement(Spend spend) {
        try {
            if (!budgetLineRepository.findById(spend.getBudgetLine().getId()).isPresent()) {
                return ResponseBody.error("Cette ligne n'existe déjà !!!");
            }
            Spend newSpend = spendRepository.save(spend);
            return ResponseBody.with(newSpend, "Dépense ajouter avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !!!");
        }
    }

    public ResponseBody upload(Long id, MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseBody.error("Le fichier est vide");
        }
        try {
            Optional<Spend> optionalSpend = spendRepository.findById(id);
            if (!optionalSpend.isPresent()) {
                return ResponseBody.error("Cette dépense n'existe pas !!!");
            }

            Spend spend = optionalSpend.get();
            spend.setPictures(file.getOriginalFilename());
            spend.setState(Enumeration.SPEND_STATE.APPROVED);
            storageService.save(file);
            Spend newSpend = spendRepository.save(spend);
            return ResponseBody.with(newSpend, "Fichier uploader avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !!!");
        }
    }

    public ResponseBody validate(Spend spend) {
        try {
            if (!budgetLineRepository.findById(spend.getBudgetLine().getId()).isPresent()) {
                return ResponseBody.error("Cette ligne n'existe déjà !!!");
            }
            Spend oldSpend = spendRepository.findSpendById(spend.getId());
            List<Spend> spends = spendRepository.findAllByBudgetLineId(oldSpend.getBudgetLine().getId());
            BudgetLine line = budgetLineRepository.findBudgetLineById(oldSpend.getBudgetLine().getId());
            line.setRealized(
                    Optional.of(spends
                            .stream()
                            .map(spend1 -> spend1.getAmount().toString())
                            .mapToDouble(Double::parseDouble)
                            .sum())
                            .orElse(0.0)
            );
            line.setDifference(line.getTotal() - line.getRealized());
            line.setUpdateDate(new Date());
            oldSpend.setState(Enumeration.SPEND_STATE.VALIDATION);
            spendRepository.save(oldSpend);
            return ResponseBody.with(budgetLineRepository.save(line),"Décaissement validé avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !!!");
        }
    }
}
