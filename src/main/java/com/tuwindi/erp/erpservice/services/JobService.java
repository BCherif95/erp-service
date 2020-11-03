package com.tuwindi.erp.erpservice.services;

import com.tuwindi.erp.erpservice.entities.Job;
import com.tuwindi.erp.erpservice.repositories.JobRepository;
import com.tuwindi.erp.erpservice.utils.PageBody;
import com.tuwindi.erp.erpservice.utils.ResponseBody;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public Page<Job> getAll(PageBody pageBody) {
        Sort sort = Sort.by(pageBody.getSortdirection(), pageBody.getSortBy());
        Pageable pageable = PageRequest.of(pageBody.getPageNumber(), pageBody.getPageSize(), sort);
        return jobRepository.findAll(pageable);
    }

    public ResponseBody findAll() {
        try {
            return ResponseBody.with(jobRepository.findAll(), "Liste des proféssions !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !");
        }
    }

    public ResponseBody getById(Long id) {
        try {
            Optional<Job> optionalUnity = jobRepository.findById(id);
            return optionalUnity
                    .map(value -> ResponseBody.with(value, "Recuperer avec succes!"))
                    .orElseGet(() -> ResponseBody.error("Une erreur est survenue!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!");
        }
    }

    public ResponseBody create(Job job) {
        try {
            if (jobRepository.existsByTitle(job.getTitle())) {
                return ResponseBody.error("Cette proféssion existe déjà !!!");
            }
            return ResponseBody.with(jobRepository.save(job), "Ajouter avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !!!");
        }
    }

    public ResponseBody update(Job job) {
        try {
            Optional<Job> jobOptional = jobRepository.findById(job.getId());
            if (jobOptional.isPresent()) {
                boolean isExist = jobRepository.existsDistinctByTitleAndId(job.getTitle(),job.getId());
                if (!isExist) {
                    return ResponseBody.error("Cette proféssion existe déjà !");
                }
                return ResponseBody.with(jobRepository.save(job), "Modifier avec succes !");
            } else {
                return ResponseBody.error("Cette proféssion n'existe pas!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!");
        }
    }
}
