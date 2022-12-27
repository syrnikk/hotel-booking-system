package com.syrnik.hotelbookingapi.model;

import lombok.Data;

@Data
public class Room {
    private String roomNumber;
    private int floor;
    private Hotel hotel;
    private RoomType roomType;
}
