package com.tuwindi.erp.erpservice.controllers;

import com.tuwindi.erp.erpservice.entities.Unity;
import com.tuwindi.erp.erpservice.services.UnitService;
import com.tuwindi.erp.erpservice.utils.PageBody;
import com.tuwindi.erp.erpservice.utils.SearchBody;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/units")
@AllArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @PostMapping("all")
    public ResponseEntity<Page<Unity>> getAll(@RequestBody PageBody pageBody) {
        return ResponseEntity.ok(unitService.getAll(pageBody));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(unitService.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(unitService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Unity unity) {
        return ResponseEntity.ok(unitService.create(unity));
    }

    @PostMapping("/by-type")
    public ResponseEntity<?> getAllByType(@RequestBody SearchBody searchBody) {
        return ResponseEntity.ok(unitService.getAllByType(searchBody));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Unity unity) {
        return ResponseEntity.ok(unitService.update(unity));
    }
}
