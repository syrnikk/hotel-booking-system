package com.syrnik.hotelbookingapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private Long id;
    private String street;
    private String number;
    private String postalCode;
    private City city;
}
