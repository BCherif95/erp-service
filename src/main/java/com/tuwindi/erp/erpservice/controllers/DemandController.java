package com.tuwindi.erp.erpservice.controllers;

import com.tuwindi.erp.erpservice.entities.Demand;
import com.tuwindi.erp.erpservice.services.DemandService;
import com.tuwindi.erp.erpservice.utils.PageBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demands")
@AllArgsConstructor
public class DemandController {

    private final DemandService demandService;

    @PostMapping("/all")
    public ResponseEntity<?> getAll(@RequestBody PageBody pageBody) {
        return ResponseEntity.ok(demandService.getAll(pageBody));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(demandService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(demandService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Demand demand) {
        return ResponseEntity.ok(demandService.create(demand));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Demand demand) {
        return ResponseEntity.ok(demandService.update(demand));
    }

    @PostMapping("approve")
    public ResponseEntity<?> approve(@RequestBody Demand demand) {
        return ResponseEntity.ok(demandService.approved(demand));
    }

    @PostMapping("validate")
    public ResponseEntity<?> validate(@RequestBody Demand demand) {
        return ResponseEntity.ok(demandService.validate(demand));
    }


}
