package com.tuwindi.erp.erpservice.controllers;

import com.tuwindi.erp.erpservice.entities.Department;
import com.tuwindi.erp.erpservice.services.DepartementService;
import com.tuwindi.erp.erpservice.utils.PageBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departements")
@AllArgsConstructor
public class DepartementController {

    private final DepartementService departementService;

    @PostMapping("all")
    public ResponseEntity<?> getAll(@RequestBody PageBody pageBody) {
        return ResponseEntity.ok(departementService.getAll(pageBody));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(departementService.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(departementService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Department department) {
        return ResponseEntity.ok(departementService.create(department));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Department department) {
        return ResponseEntity.ok(departementService.update(department));
    }
}
