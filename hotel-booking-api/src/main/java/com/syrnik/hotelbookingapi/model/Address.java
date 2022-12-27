package com.syrnik.hotelbookingapi.model;

import lombok.Data;

@Data
public class Address {
    private String street;
    private String number;
    private String postalCode;
    private City city;
}
