package com.syrnik.hotelbookingapi.controller;

import com.syrnik.hotelbookingapi.dto.*;
import com.syrnik.hotelbookingapi.security.TokenService;
import com.syrnik.hotelbookingapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenService.generateToken(authentication);

        UserDto userDto = new UserDto();
        if(authentication.getPrincipal() instanceof UserDetails userDetails) {
            userDto.setEmail(userDetails.getUsername());
            userDto.setRoles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        }
        return ResponseEntity.ok(new JwtResponse(jwt, userDto));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage> register(@RequestBody RegisterRequest registerRequest) {
        if (userService.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error: email is already taken!"));
        }
        userService.save(registerRequest);
        return ResponseEntity.ok(new ResponseMessage("User registered successfully!"));
    }
}
