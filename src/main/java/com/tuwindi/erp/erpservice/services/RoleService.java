package com.tuwindi.erp.erpservice.services;

import com.tuwindi.erp.erpservice.entities.Role;
import com.tuwindi.erp.erpservice.repositories.RoleRepository;
import com.tuwindi.erp.erpservice.utils.ResponseBody;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public ResponseBody getAll() {
        try {
            return ResponseBody.with(roleRepository.findAll(), "Liste de role!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue");
        }
    }

    public ResponseBody getByName(String name) {
        try {
            return ResponseBody.with(roleRepository.findByName(name), "Role");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue");
        }
    }

    public ResponseBody save(Role role) {
        try {
            if (roleRepository.existsByName(role.getName())) {
                roleRepository.save(role);
            }
            return ResponseBody.with(role, "Role modifie avec succes!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!");
        }
    }
}
