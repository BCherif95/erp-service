package com.tuwindi.erp.erpservice.controllers;

import com.tuwindi.erp.erpservice.entities.Budget;
import com.tuwindi.erp.erpservice.services.BudgetService;
import com.tuwindi.erp.erpservice.utils.BudgetSaveEntity;
import com.tuwindi.erp.erpservice.utils.PageBody;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budgets")
@AllArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping("/all")
    public ResponseEntity<Page<Budget>> getAll(@RequestBody PageBody pageBody) {
        return ResponseEntity.ok(budgetService.getAll(pageBody));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(budgetService.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(budgetService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BudgetSaveEntity budgetSaveEntity) {
        return ResponseEntity.ok(budgetService.create(budgetSaveEntity));
    }
}
