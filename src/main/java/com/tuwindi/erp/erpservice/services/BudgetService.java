package com.tuwindi.erp.erpservice.services;

import com.tuwindi.erp.erpservice.entities.Budget;
import com.tuwindi.erp.erpservice.entities.BudgetLine;
import com.tuwindi.erp.erpservice.entities.Section;
import com.tuwindi.erp.erpservice.helper.ExcelHelper;
import com.tuwindi.erp.erpservice.repositories.BudgetRepository;
import com.tuwindi.erp.erpservice.repositories.SectionRepository;
import com.tuwindi.erp.erpservice.utils.BudgetSaveEntity;
import com.tuwindi.erp.erpservice.utils.PageBody;
import com.tuwindi.erp.erpservice.utils.ResponseBody;
import com.tuwindi.erp.erpservice.utils.SectionBody;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final BudgetLineService budgetLineService;
    private final SectionRepository sectionRepository;

    public Page<Budget> getAll(PageBody pageBody) {
        Sort sort = Sort.by(pageBody.getSortdirection(), pageBody.getSortBy());
        Pageable pageable = PageRequest.of(pageBody.getPageNumber(), pageBody.getPageSize(), sort);
        return budgetRepository.findAll(pageable);
    }


    public ResponseBody create(BudgetSaveEntity budgetSaveEntity) {
        try {
            if (isEmpty(budgetSaveEntity.getSections())) {
                return ResponseBody.error("Liste vide !");
            }

            Budget budget = budgetSaveEntity.getBudget();
            //Save budget
            Budget newBudget = budgetRepository.saveAndFlush(budget);

            //lines Budget
            budgetSaveEntity.getSections()
                    .forEach(sectionBody -> {
                        Section section = new Section();
                        section.setTitle(sectionBody.getName());
                        //save section
                        Section newSection = sectionRepository.saveAndFlush(section);
                        sectionBody.getLines()
                                .stream()
                                .peek(line -> setterBudgetAndSection(line, newSection, newBudget))
                                .forEach(this::saveLine);
                    });

            assignAmount(newBudget, budgetLineService.findAllByBudgetId(newBudget.getId()));
            //update Budget after calculate amount
            Budget updateBudget = budgetRepository.save(newBudget);
            return ResponseBody.with(updateBudget, "Ajouter avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue");
        }
    }

    public ResponseBody getById(Long id) {
        try {
            Optional<Budget> optionalBudget = budgetRepository.findById(id);
            return optionalBudget
                    .map(value -> ResponseBody.with(value, "Recuperer avec succes!"))
                    .orElseGet(() -> ResponseBody.error("Une erreur est survenue!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!");
        }
    }


    public ResponseBody findAll() {
        try {
            return ResponseBody.with(budgetRepository.findAll(), "Liste des budgets !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !");
        }
    }

    private void saveLine(BudgetLine budgetLine) {
        this.budgetLineService.create(budgetLine);
    }

    private void assignAmount(Budget budget, List<BudgetLine> budgetLines) {
        budget.setAmount(
                Optional.of(budgetLines
                        .stream()
                        .map(line -> line.getTotal().toString())
                        .mapToDouble(Double::parseDouble)
                        .sum())
                        .orElse(0.0)
        );
    }

    private void setterBudgetAndSection(BudgetLine budgetLine, Section section, Budget budget) {
        budgetLine.setStayToFinance(budgetLine.getTotal());
        budgetLine.setSection(section);
        budgetLine.setBudget(budget);
    }

    private boolean isEmpty(Collection<SectionBody> collections) {
        return collections == null || collections.isEmpty();
    }

}
