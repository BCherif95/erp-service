package com.tuwindi.erp.erpservice.controllers;

import com.tuwindi.erp.erpservice.services.BudgetLineService;
import com.tuwindi.erp.erpservice.utils.PageBody;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budget-lines")
@AllArgsConstructor
public class BudgetLineController {

    private final BudgetLineService budgetLineService;

   /* @GetMapping("/by-budget/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(budgetLineService.findAllByBudgetId(id));
    }*/

    @PostMapping("/by-budget")
    public ResponseEntity<?> findAllElementByBudget(@RequestBody PageBody pageBody) {
        return ResponseEntity.ok(budgetLineService.findAllElementByBudget(pageBody));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable Long id) {
        String filename = "FormatStandard.xlsx";
        InputStreamResource file = new InputStreamResource(budgetLineService.downloadCsv(id));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
