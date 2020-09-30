package com.tuwindi.erp.erpservice.services;

import com.tuwindi.erp.erpservice.entities.User;
import com.tuwindi.erp.erpservice.exception.CustomException;
import com.tuwindi.erp.erpservice.repositories.UserRepository;
import com.tuwindi.erp.erpservice.utils.PageBody;
import com.tuwindi.erp.erpservice.utils.ResponseBody;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseBody listOfUsers(PageBody pageBody) {
        try {
           /* Sort sort = Sort.by(pageBody.getSortdirection(), pageBody.getSortBy());
            Pageable pageable = PageRequest.of(pageBody.getPageNumber(), pageBody.getPageSize(), sort);
            Page<User> users = userRepository.findAll(pageable);*/
            return ResponseBody.with(userRepository.findAll(), "Liste des utilisateurs !!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est survenue!!!");
        }
    }

    public ResponseBody create(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreateDate(new Date());
            User utilisateur = userRepository.save(user);
            return ResponseBody.with(utilisateur, "Utilisateur ajoutee avec succes");
        } else {
            throw new CustomException("Ce nom d'utilisateur est déjà utilisé", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseBody edit(User user) {
        try {
            // if empty so => not exist, else => exist
            boolean existingUser = userRepository.checkExistingUser(user.getId(), user.getUsername()).isEmpty();
            if (!existingUser) {
                return ResponseBody.error("Ce nom d'utilisateur existe deja");
            }
            User oldUser = userRepository.findUserById(user.getId());
            if (oldUser == null) {
                return ResponseBody.error("Cet utilisateur n'existe pas");
            }
            if (!StringUtils.isEmpty(user.getPassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            } else {
                user.setPassword(oldUser.getPassword());
            }
            user.setUpdateDate(new Date());
            userRepository.save(user);
            return ResponseBody.with(user, "Utilisateur modifie avec succes!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est servenue!");
        }
    }

    public ResponseBody remove(Long id) {
        try {
            if (userRepository.findById(id).isPresent()) {
                userRepository.deleteById(id);
            }
            return ResponseBody.success("Suppression avec succes");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Une erreur est servenue");
        }
    }

    public ResponseBody getUser(Long id) {
        try {
            return ResponseBody.with(userRepository.findUserById(id), "Recuperer avec succes");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.error("Ce nom existe deja!");
        }
    }
}
