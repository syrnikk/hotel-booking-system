package com.syrnik.hotelbookingapi.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomType {
    private long id;
    private String type;
    private int amountOfPeople;
    private BigDecimal price;
    private String description;
}
