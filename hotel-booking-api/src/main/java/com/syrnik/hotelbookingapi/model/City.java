package com.syrnik.hotelbookingapi.model;

import lombok.Data;

@Data
public class City {
    private long id;
    private String name;
    private Country country;
}
