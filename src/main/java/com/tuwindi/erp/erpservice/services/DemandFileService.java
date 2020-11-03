package com.tuwindi.erp.erpservice.services;

import com.tuwindi.erp.erpservice.entities.Demand;
import com.tuwindi.erp.erpservice.entities.DemandFile;
import com.tuwindi.erp.erpservice.repositories.DemandFileRepository;
import com.tuwindi.erp.erpservice.repositories.DemandRepository;
import com.tuwindi.erp.erpservice.upload.UploadDemandFile;
import com.tuwindi.erp.erpservice.utils.Enumeration;
import com.tuwindi.erp.erpservice.utils.ResponseBody;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DemandFileService {
    private final DemandFileRepository demandFileRepository;
    private final DemandRepository demandRepository;
    private final UploadDemandFile uploadDemandFile;

    public ResponseBody listFileByDemandId(Long id) {
        try {
            if (!demandRepository.findById(id).isPresent()) {
                return ResponseBody.error("Une erreur est survenue !!!");
            }
            return ResponseBody.with(demandFileRepository.findAllByDemandId(id), "Liste des fichiers");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !!!");
        }
    }

    public ResponseBody choiceOfFile(DemandFile demandFile) {
        try {
            if (!demandFileRepository.findById(demandFile.getId()).isPresent()) {
                return ResponseBody.error("Cet objet n'existe pas !!!");
            }
            demandFile.setStatus(true);
            return ResponseBody.with(demandFileRepository.save(demandFile), "Demande approuvé avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !!!");
        }
    }

    public ResponseBody approve(Long id, MultipartFile[] uploadFiles) {
        if (uploadFiles.length <= 0) {
            return ResponseBody.error("Le fichier est vide");
        }
        try {
            Optional<Demand> optionalDemand = demandRepository.findById(id);
            if (!optionalDemand.isPresent()) {
                return ResponseBody.error("Cette demande n'existe pas !!!");
            }

            Demand demand = optionalDemand.get();
            Arrays.stream(uploadFiles)
                    .forEach(file -> {
                        DemandFile demandFile = new DemandFile();
                        demandFile.setDemand(demand);
                        demandFile.setFileName(file.getOriginalFilename());
                        uploadDemandFile.save(file);
                        demandFileRepository.save(demandFile);
                    });
            demand.setDemandState(Enumeration.DEMAND_STATE.IN_VALIDATION);
            return ResponseBody.with(demandRepository.save(demand), "Approuver avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !!!");
        }
    }
}
