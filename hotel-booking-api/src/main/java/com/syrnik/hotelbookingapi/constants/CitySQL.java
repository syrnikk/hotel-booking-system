package com.syrnik.hotelbookingapi.constants;

public class CitySQL {
    public static final String SELECT_CITIES_BY_COUNTRY_NAME_SQL =
            "SELECT miasto_nazwa FROM miasto_view where kraj_nazwa = ?";
}
