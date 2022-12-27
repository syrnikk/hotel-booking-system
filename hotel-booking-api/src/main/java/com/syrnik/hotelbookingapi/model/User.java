package com.syrnik.hotelbookingapi.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private List<Role> roles;
    private boolean active;
}
