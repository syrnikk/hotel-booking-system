package com.syrnik.hotelbookingapi.constants;

public class RateSQL {
    public static final String ADD_RATE_SQL =
            "INSERT INTO ocena(ocena, uzytkownik_id, hotel_id) VALUES(?, ?, ?)";

    public static final String UPDATE_RATE_SQL =
            "UPDATE ocena SET ocena = ? WHERE id = ?";

    public static final String FIND_RATE_BY_USER_AND_HOTEL_SQL =
            "SELECT * FROM ocena WHERE uzytkownik_id = ? AND hotel_id = ?";
}
