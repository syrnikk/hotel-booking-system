package com.syrnik.hotelbookingapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomType {
    private Long id;
    private String type;
    private Integer amountOfPeople;
    private BigDecimal price;
    private String description;
}
