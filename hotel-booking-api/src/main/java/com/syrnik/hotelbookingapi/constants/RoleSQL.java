package com.syrnik.hotelbookingapi.constants;

public class RoleSQL {
    public static final String SELECT_ROLE_ID_BY_NAME_SQL =
            "SELECT id FROM rola WHERE nazwa = ?";

    public static final String INSERT_ROLE_SQL =
            "INSERT INTO rola(nazwa) VALUES (?)";
}
