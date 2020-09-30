package com.tuwindi.erp.erpservice.controllers;

import com.tuwindi.erp.erpservice.entities.Partner;
import com.tuwindi.erp.erpservice.services.PartnerService;
import com.tuwindi.erp.erpservice.utils.PageBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partners")
@AllArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    @PostMapping("all")
    public ResponseEntity<?> getAll(@RequestBody PageBody pageBody) {
        return ResponseEntity.ok(partnerService.getAll(pageBody));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(partnerService.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(partnerService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Partner partner) {
        return ResponseEntity.ok(partnerService.create(partner));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Partner partner) {
        return ResponseEntity.ok(partnerService.update(partner));
    }
}
