package com.syrnik.hotelbookingapi.constants;

public class HotelSQL {
    public static final String SELECT_HOTEL_BY_CITY_NAME_SQL =
            "SELECT * FROM hotel_view WHERE miasto_nazwa = ?";

    public static final String SELECT_HOTEL_SQL =
            "SELECT * FROM hotel_view";

    public static final String SELECT_HOTEL_BY_ID_SQL =
            "SELECT * FROM hotel_view WHERE id = ?";

    public static final String INSERT_HOTEL_SQL =
            "INSERT INTO hotel(nazwa, adres_id, telefon, tytul, opis, gwiazdki) VALUES (?, ?, ?, ?, ?, ?)";

    public static final String DELETE_HOTEL_BY_ID_SQL =
            "DELETE FROM hotel WHERE id = ?";
}
