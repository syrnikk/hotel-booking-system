package com.syrnik.hotelbookingapi.service;

import com.syrnik.hotelbookingapi.dao.UserDao;
import com.syrnik.hotelbookingapi.dto.RegisterRequest;
import com.syrnik.hotelbookingapi.model.Role;
import com.syrnik.hotelbookingapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;

    public void save(RegisterRequest registerRequest) {
        User user = User.builder()
                .name(registerRequest.getName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .phone(registerRequest.getPhone())
                .roles(List.of(new Role("ROLE_USER")))
                .build();
        userDao.save(user);
    }

    public boolean existsByEmail(String email) {
        Optional<User> optionalUser = userDao.findByEmail(email);
        return optionalUser.isPresent();
    }
}
