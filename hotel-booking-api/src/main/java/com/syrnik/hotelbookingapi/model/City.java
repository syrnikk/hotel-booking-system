package com.syrnik.hotelbookingapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class City {
    private Long id;
    private String name;
    private Country country;
}
