package com.tuwindi.erp.erpservice.controllers;

import com.tuwindi.erp.erpservice.entities.DemandFile;
import com.tuwindi.erp.erpservice.services.DemandFileService;
import com.tuwindi.erp.erpservice.upload.UploadDemandFile;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/demandFiles")
@AllArgsConstructor
public class DemandFileController {

    private final DemandFileService demandFileService;
    private final UploadDemandFile uploadDemandFile;

    @PostMapping("/approve")
    @ResponseBody
    public ResponseEntity<?> upload(@RequestParam("id") Long id, @RequestParam("files") MultipartFile[] uploadFiles) {
        return ResponseEntity.ok(demandFileService.approve(id, uploadFiles));
    }

    @PostMapping("/chooce")
    public ResponseEntity<?> chooce(@RequestBody DemandFile demandFile) {
        return ResponseEntity.ok(demandFileService.choiceOfFile(demandFile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listFileByDemandId(@PathVariable Long id) {
        return ResponseEntity.ok(demandFileService.listFileByDemandId(id));
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = uploadDemandFile.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
