package com.tuwindi.erp.erpservice.controllers;

import com.tuwindi.erp.erpservice.entities.Project;
import com.tuwindi.erp.erpservice.services.ProjectService;
import com.tuwindi.erp.erpservice.utils.PageBody;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@AllArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("all")
    public ResponseEntity<Page<Project>> getAll(@RequestBody PageBody pageBody) {
        return ResponseEntity.ok(projectService.getAll(pageBody));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.create(project));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.update(project));
    }
}
