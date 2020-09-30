package com.tuwindi.erp.erpservice.controllers;

import com.tuwindi.erp.erpservice.entities.Category;
import com.tuwindi.erp.erpservice.services.CategoryService;
import com.tuwindi.erp.erpservice.utils.PageBody;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("all")
    public ResponseEntity<Page<Category>> getAll(@RequestBody PageBody pageBody) {
        return ResponseEntity.ok(categoryService.getAll(pageBody));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.create(category));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.update(category));
    }
}
