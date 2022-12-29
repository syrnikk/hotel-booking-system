package com.syrnik.hotelbookingapi.service;

import com.syrnik.hotelbookingapi.dao.RoleDao;
import com.syrnik.hotelbookingapi.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleDao roleDao;

    public void save(Role role) {
        roleDao.save(role);
    }
}
