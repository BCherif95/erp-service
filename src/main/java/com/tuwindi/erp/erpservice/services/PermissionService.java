package com.tuwindi.erp.erpservice.services;

import com.tuwindi.erp.erpservice.repositories.PermissionRepository;
import com.tuwindi.erp.erpservice.utils.ResponseBody;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public ResponseBody getAll() {
        try {
            return ResponseBody.with(permissionRepository.findAll(), "Liste de privilige!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue");
        }
    }
}

