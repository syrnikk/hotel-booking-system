package com.syrnik.hotelbookingapi.controller;

import com.syrnik.hotelbookingapi.model.Role;
import com.syrnik.hotelbookingapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/role")
    public ResponseEntity<String> addRole(@RequestBody Role role) {
        roleService.save(role);
        return ResponseEntity.ok("Role added successfully");
    }
}
