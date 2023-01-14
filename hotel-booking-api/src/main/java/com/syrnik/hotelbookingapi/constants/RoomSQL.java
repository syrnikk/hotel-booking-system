package com.syrnik.hotelbookingapi.constants;

public class RoomSQL {
    public static final String SELECT_AVAILABLE_ROOMS_GROUP_BY_TYPE_SQL =
            """
                SELECT tp.*, COUNT(*) AS ilosc_pokoji from get_dostepne_pokoje(?, ?, ?) p
                INNER JOIN typ_pokoju tp ON p.typ_pokoju_id = tp.id GROUP BY tp.id;
            """;

    public static final String SELECT_AVAILABLE_ROOMS_BY_ROOM_TYPE_SQL =
            "SELECT p.id from get_dostepne_pokoje(?, ?, ?) p WHERE p.typ_pokoju_id = ?";

    public static final String SELECT_ROOMS_SQL =
            "SELECT * FROM pokoj_view";

    public static final String INSERT_ROOM_SQL =
            "INSERT INTO pokoj(typ_pokoju_id, hotel_id, numer_pokoju, pietro) VALUES (?, ?, ?, ?)";

    public static final String DELETE_ROOM_BY_ID_SQL =
            "DELETE FROM pokoj WHERE id = ?";
}
