package com.syrnik.hotelbookingapi.model;

import lombok.Data;

@Data
public class Room {
    private Long id;
    private String roomNumber;
    private Integer floor;
    private Hotel hotel;
    private RoomType roomType;
}
