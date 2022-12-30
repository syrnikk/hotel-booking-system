package com.syrnik.hotelbookingapi.model;

import lombok.Data;

@Data
public class Hotel {
    private long id;
    private String name;
    private Address address;
    private String phone;
    private String title;
    private String description;
    private int stars;
}
