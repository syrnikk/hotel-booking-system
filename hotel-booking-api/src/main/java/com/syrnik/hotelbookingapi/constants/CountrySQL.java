package com.syrnik.hotelbookingapi.constants;

public class CountrySQL {
    public static final String INSERT_COUNTRY_SQL =
            "INSERT INTO kraj(nazwa) VALUES (?)";

    public static final String SELECT_COUNTRIES_SQL =
            "SELECT * FROM kraj";

    public static final String DELETE_COUNTRY_BY_ID_SQL =
            "DELETE FROM kraj WHERE id = ?";

    public static final String SELECT_COUNTRY_BY_ID_SQL =
            "SELECT * FROM kraj WHERE id = ?";

    public static final String UPDATE_COUNTRY_SQL =
            "UPDATE kraj SET nazwa = ? WHERE id = ?";
}
