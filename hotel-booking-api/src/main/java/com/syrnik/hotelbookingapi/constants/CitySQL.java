package com.syrnik.hotelbookingapi.constants;

public class CitySQL {
    public static final String SELECT_CITIES_BY_COUNTRY_NAME_SQL =
            "SELECT id, miasto_nazwa FROM miasto_view where kraj_nazwa = ?";

    public static final String SELECT_CITIES_SQL =
            "SELECT m.id, m.nazwa, k.id as kraj_id, k.nazwa as kraj_nazwa FROM miasto m INNER JOIN kraj k ON k.id = m.kraj_id";

    public static final String DELETE_CITY_BY_ID_SQL =
            "DELETE FROM miasto WHERE id = ?";

    public static final String INSERT_CITY_SQL =
            "INSERT INTO miasto(nazwa, kraj_id) VALUES (?, ?)";

    public static final String SELECT_CITY_BY_ID_SQL =
            "SELECT m.id, m.nazwa, k.id as kraj_id, k.nazwa as kraj_nazwa FROM miasto m INNER JOIN kraj k ON k.id = m.kraj_id WHERE m.id = ?";

    public static final String UPDATE_CITY_SQL =
            "UPDATE miasto SET kraj_id = ?, nazwa = ? WHERE id = ?";

}
