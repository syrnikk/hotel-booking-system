package com.syrnik.hotelbookingapi.model;

import lombok.Data;

@Data
public class Rate {
    private Long id;
    private Long rate;
    private User user;
    private Hotel hotel;
}
