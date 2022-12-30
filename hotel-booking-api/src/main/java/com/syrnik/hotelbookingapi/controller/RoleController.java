package com.syrnik.hotelbookingapi.controller;

import com.syrnik.hotelbookingapi.dao.RoleDao;
import com.syrnik.hotelbookingapi.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoleController {
    private final RoleDao roleDao;

    @GetMapping("/role")
    public ResponseEntity<List<Role>> getRoles() {
        try {
            List<Role> roles = roleDao.find();
            if(roles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(roles);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/role")
    public ResponseEntity<String> addRole(@RequestBody Role role) {
        try {
            roleDao.save(role);
            return ResponseEntity.ok("Role added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<String> getRoles(@PathVariable Long id) {
        try {
            roleDao.deleteById(id);
            return ResponseEntity.ok("Role deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
