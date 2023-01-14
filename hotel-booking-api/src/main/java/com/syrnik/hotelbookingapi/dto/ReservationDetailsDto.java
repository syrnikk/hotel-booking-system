package com.syrnik.hotelbookingapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDetailsDto {
    private Long reservationId;
    private String hotelName;
    private String roomType;
    private BigDecimal price;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer roomAmount;
    private String comment;
}
