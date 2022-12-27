package com.syrnik.hotelbookingapi.model;

import lombok.Data;

@Data
public class Rate {
    private long rate;
    private User user;
    private Hotel hotel;
}
