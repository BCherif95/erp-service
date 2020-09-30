package com.tuwindi.erp.erpservice.controllers;

import com.tuwindi.erp.erpservice.entities.Role;
import com.tuwindi.erp.erpservice.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        return ResponseEntity.ok(roleService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.save(role));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.save(role));
    }
}
