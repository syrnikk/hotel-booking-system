package com.syrnik.hotelbookingapi.constants;

public class HotelSQL {
    public static final String SELECT_HOTEL_BY_CITY_NAME_SQL =
            "SELECT * FROM hotel_view WHERE miasto_nazwa = ?";

    public static final String SELECT_HOTEL_BY_ID_SQL =
            "SELECT * FROM hotel_view WHERE id = ?";
}
