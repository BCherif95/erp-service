package com.tuwindi.erp.erpservice.services;

import com.tuwindi.erp.erpservice.entities.BudgetLine;
import com.tuwindi.erp.erpservice.helper.ExcelHelper;
import com.tuwindi.erp.erpservice.repositories.BudgetLineRepository;
import com.tuwindi.erp.erpservice.utils.PageBody;
import com.tuwindi.erp.erpservice.utils.ResponseBody;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@AllArgsConstructor
public class BudgetLineService {

    private final BudgetLineRepository budgetLineRepository;

    public ResponseBody create(BudgetLine budgetLine) {
        try {
            return ResponseBody.with(budgetLineRepository.save(budgetLine), "Ajouter avec succes!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est servenue!");
        }
    }

    public List<BudgetLine> findAllByBudgetId(Long id) {
        return budgetLineRepository.findAllByBudgetId(id);
    }

    public Page<BudgetLine> findAllElementByBudget(PageBody pageBody) {
        Sort sort = Sort.by(pageBody.getSortdirection(), pageBody.getSortBy());
        Pageable pageable = PageRequest.of(pageBody.getPageNumber(), pageBody.getPageSize(), sort);
        return budgetLineRepository.findAllByBudgetId(pageBody.getBudgetId(), pageable);
    }

    public ByteArrayInputStream downloadCsv(Long id) {
        List<BudgetLine> budgetLines = budgetLineRepository.findAllByBudgetId(id);

        ByteArrayInputStream in = ExcelHelper.budgetToExcel(budgetLines);
        return in;
    }
}
