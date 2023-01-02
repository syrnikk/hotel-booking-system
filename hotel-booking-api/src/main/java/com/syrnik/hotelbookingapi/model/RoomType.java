package com.syrnik.hotelbookingapi.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomType {
    private Long id;
    private String type;
    private Integer amountOfPeople;
    private BigDecimal price;
    private String description;
}
