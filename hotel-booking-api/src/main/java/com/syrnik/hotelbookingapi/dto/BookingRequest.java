package com.syrnik.hotelbookingapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    private Long hotelId;
    private Long roomTypeId;
    private Integer roomAmount;
    private LocalDate startDate;
    private LocalDate endDate;
}
