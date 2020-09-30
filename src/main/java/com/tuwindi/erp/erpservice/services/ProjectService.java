package com.tuwindi.erp.erpservice.services;

import com.tuwindi.erp.erpservice.entities.Project;
import com.tuwindi.erp.erpservice.repositories.ProjectRepository;
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
public class ProjectService {
    private final ProjectRepository projectRepository;

    public Page<Project> getAll(PageBody pageBody) {
        Sort sort = Sort.by(pageBody.getSortdirection(), pageBody.getSortBy());
        Pageable pageable = PageRequest.of(pageBody.getPageNumber(), pageBody.getPageSize(), sort);
        return projectRepository.findAll(pageable);
    }

    public ResponseBody findAll() {
        try {
            return ResponseBody.with(projectRepository.findAll(), "Liste de projets !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !");
        }
    }

    public ResponseBody getById(Long id) {
        try {
            Optional<Project> optionalProject = projectRepository.findById(id);
            return optionalProject
                    .map(value -> ResponseBody.with(value, "Recuperer avec succes!"))
                    .orElseGet(() -> ResponseBody.error("Une erreur est survenue!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!");
        }
    }

    public ResponseBody create(Project project) {
        try {
            if (projectRepository.existsByTitle(project.getTitle())) {
                return ResponseBody.error("Ce projet existe déjà !!!");
            }
            return ResponseBody.with(projectRepository.save(project), "Ajouter avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue !");
        }
    }

    public ResponseBody update(Project project) {
        try {
            Optional<Project> projectOptional = projectRepository.findById(project.getId());
            if (projectOptional.isPresent()) {
                boolean isExist = projectRepository.findDistinctByIdAndTitle(project.getId(), project.getTitle()).isEmpty();
                if (!isExist) {
                    return ResponseBody.error("Ce projet existe déjà !");
                }
                return ResponseBody.with(projectRepository.save(project), "Modifier avec succes !");
            } else {
                return ResponseBody.error("Cet projet n'existe pas!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!");
        }
    }
}
