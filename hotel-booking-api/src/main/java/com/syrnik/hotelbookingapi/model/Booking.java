package com.syrnik.hotelbookingapi.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Booking {
    private User user;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private String comment;
}
