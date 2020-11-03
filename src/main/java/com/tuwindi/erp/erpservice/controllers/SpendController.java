package com.tuwindi.erp.erpservice.controllers;

import com.tuwindi.erp.erpservice.entities.Spend;
import com.tuwindi.erp.erpservice.services.SpendService;
import com.tuwindi.erp.erpservice.upload.FilesStorageService;
import com.tuwindi.erp.erpservice.utils.PageBody;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/spends")
@AllArgsConstructor
public class SpendController {

    private final SpendService spendService;
    private final FilesStorageService storageService;

    @PostMapping("/all")
    public ResponseEntity<Page<Spend>> getAll(@RequestBody PageBody pageBody) {
        return ResponseEntity.ok(spendService.getAll(pageBody));
    }

    @PostMapping("/disbursement")
    public ResponseEntity<?> disbursement(@RequestBody Spend spend) {
        return ResponseEntity.ok(spendService.disbursement(spend));
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestBody Spend spend) {
        return ResponseEntity.ok(spendService.validate(spend));
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<?> upload(@RequestParam("id") Long id, @RequestParam("uploadfile") MultipartFile uploadFiles) {
        return ResponseEntity.ok(spendService.upload(id, uploadFiles));
    }

    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        System.out.println("Images");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
