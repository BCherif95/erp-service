package com.tuwindi.erp.erpservice.services;

import com.tuwindi.erp.erpservice.entities.Budget;
import com.tuwindi.erp.erpservice.entities.BudgetLine;
import com.tuwindi.erp.erpservice.entities.LinePartner;
import com.tuwindi.erp.erpservice.repositories.BudgetLineRepository;
import com.tuwindi.erp.erpservice.repositories.LinePartnerRepository;
import com.tuwindi.erp.erpservice.utils.Enumeration;
import com.tuwindi.erp.erpservice.utils.PageBody;
import com.tuwindi.erp.erpservice.utils.ResponseBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LinePartnerService {
    private final LinePartnerRepository linePartnerRepository;
    private final BudgetLineRepository budgetLineRepository;
    String MESSAGE_OK;

    public LinePartnerService(LinePartnerRepository linePartnerRepository, BudgetLineRepository budgetLineRepository) {
        this.linePartnerRepository = linePartnerRepository;
        this.budgetLineRepository = budgetLineRepository;
    }

    public ResponseBody save(LinePartner linePartner) {
        try {
            MESSAGE_OK = "Financement effectué avec succes";
            BudgetLine budgetLine = budgetLineRepository.findBudgetLineById(linePartner.getBudgetLine().getId());
            if (budgetLine == null) {
                return ResponseBody.error("Cette ligne n'existe pas !!!");
            }
            linePartner.setBudgetLine(budgetLine);
            linePartner.setBalanceBefore(budgetLine.getStayToFinance());

            double newBalance = linePartner.getBalanceBefore() - linePartner.getAmount();
            linePartner.setBalanceAfter(newBalance);
            LinePartner newLinePartner = linePartnerRepository.save(linePartner);

            //update lineBudget after financing
            budgetLine.setStayToFinance(newBalance);
            budgetLine.setFinance(newLinePartner.getAmount() + budgetLine.getFinance());
            budgetLine.setUpdateDate(new Date());
            if (budgetLine.getStayToFinance() == 0) {
                budgetLine.setState(Enumeration.LINE_STATE.CLOSE);
                MESSAGE_OK += ", la ligne est totalement soldée";
            } else {
                budgetLine.setState(Enumeration.LINE_STATE.IN_FINANCING);
            }
            budgetLineRepository.save(budgetLine);
            return ResponseBody.with(newLinePartner, MESSAGE_OK + " !");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue");
        }
    }

    public Page<LinePartner> findAllByBudget(PageBody pageBody) {
        Sort sort = Sort.by(pageBody.getSortdirection(), pageBody.getSortBy());
        Pageable pageable = PageRequest.of(pageBody.getPageNumber(), pageBody.getPageSize(), sort);
        return linePartnerRepository.findAllElementByBudget(pageBody.getBudgetId(),pageable);
    }

   /* public ResponseBody findAllByBudget(Long id) {
        try {
            return ResponseBody.with(linePartnerRepository.findAllElementByBudget(id), "Les financements pour ce budget !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue");
        }
    }*/
}
