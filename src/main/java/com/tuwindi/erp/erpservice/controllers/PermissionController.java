package com.tuwindi.erp.erpservice.controllers;

import com.tuwindi.erp.erpservice.services.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/privileges")
@AllArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(permissionService.getAll());
    }
}
