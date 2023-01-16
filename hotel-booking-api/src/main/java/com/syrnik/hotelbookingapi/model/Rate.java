package com.syrnik.hotelbookingapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rate {
    private Long id;
    private Double rate;
    private User user;
    private Hotel hotel;
}
