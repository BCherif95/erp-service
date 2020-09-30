package com.tuwindi.erp.erpservice.controllers;

import com.tuwindi.erp.erpservice.entities.LinePartner;
import com.tuwindi.erp.erpservice.services.LinePartnerService;
import com.tuwindi.erp.erpservice.utils.PageBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/line-partners")
@AllArgsConstructor
public class LinePartnerController {
    private final LinePartnerService linePartnerService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody LinePartner linePartner) {
        return ResponseEntity.ok(linePartnerService.save(linePartner));
    }

    @PostMapping("/by-budget")
    public ResponseEntity<?> findAllElementByBudget(@RequestBody PageBody pageBody) {
        return ResponseEntity.ok(linePartnerService.findAllByBudget(pageBody));
    }
}
