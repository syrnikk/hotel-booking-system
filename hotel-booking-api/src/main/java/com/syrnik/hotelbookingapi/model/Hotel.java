package com.syrnik.hotelbookingapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Hotel {
    private Long id;
    private String name;
    private Address address;
    private String phone;
    private String title;
    private String description;
    private int stars;
}
