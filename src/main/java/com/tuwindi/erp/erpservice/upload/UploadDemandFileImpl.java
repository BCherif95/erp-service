package com.tuwindi.erp.erpservice.upload;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class UploadDemandFileImpl implements UploadDemandFile {

    private final Path root = Paths.get("C:/Users/HP/Projects/ERP/Files/Demandes");

    @Override
    public void init() {
        try {
            if (!Files.exists(root)) {
                Files.createDirectory(root);
            }
        } catch (IOException e) {
            throw new RuntimeException("Impossible d'initialiser le dossier pour le téléchargement!");
        }
    }
    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (Exception e) {
            throw new RuntimeException("Impossible de stocker le fichier. Erreur: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            if (filename != null) {
                Path  file = root.resolve(filename);
                Resource resource = new UrlResource(file.toUri());
                if (resource.exists() || resource.isReadable()) {
                    return resource;
                }else {
                    throw new RuntimeException("Impossible de lire le fichier!");
                }
            }
            else {
                throw new RuntimeException("Ce nom de l'image est null!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Impossible de charger les fichiers!");
        }
    }
}
