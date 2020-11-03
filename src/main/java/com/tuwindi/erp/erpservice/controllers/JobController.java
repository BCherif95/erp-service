package com.tuwindi.erp.erpservice.controllers;

import com.tuwindi.erp.erpservice.entities.Job;
import com.tuwindi.erp.erpservice.services.JobService;
import com.tuwindi.erp.erpservice.utils.PageBody;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
@AllArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping("all")
    public ResponseEntity<Page<Job>> getAll(@RequestBody PageBody pageBody) {
        return ResponseEntity.ok(jobService.getAll(pageBody));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Job job) {
        return ResponseEntity.ok(jobService.create(job));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Job job) {
        return ResponseEntity.ok(jobService.update(job));
    }
}
