package com.tuwindi.erp.erpservice.controllers;

import com.tuwindi.erp.erpservice.entities.Employee;
import com.tuwindi.erp.erpservice.services.EmployeeService;
import com.tuwindi.erp.erpservice.utils.PageBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @PostMapping("all")
    public ResponseEntity<?> getAll(@RequestBody PageBody pageBody) {
        return ResponseEntity.ok(employeeService.getAll(pageBody));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.create(employee));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.update(employee));
    }
}
