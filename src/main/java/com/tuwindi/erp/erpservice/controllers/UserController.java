package com.tuwindi.erp.erpservice.controllers;

import com.tuwindi.erp.erpservice.entities.User;
import com.tuwindi.erp.erpservice.services.UserService;
import com.tuwindi.erp.erpservice.utils.PageBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> listOfUsers(PageBody pageBody) {
        return ResponseEntity.ok(userService.listOfUsers(pageBody));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user) {
        return ResponseEntity.ok(userService.edit(user));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(userService.remove(id));
    }

    @GetMapping("/{id}/getUser")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}

