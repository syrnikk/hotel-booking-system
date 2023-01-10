package com.syrnik.hotelbookingapi.constants;

public class RoomTypeSQL {
    public static final String SELECT_ROOM_TYPES_SQL =
            "SELECT id, typ, ilosc_osob, cena, opis FROM typ_pokoju";

    public static final String INSERT_ROOM_TYPE_SQL =
            "INSERT INTO typ_pokoju(typ, ilosc_osob, cena, opis) VALUES (?, ?, ?, ?)";

    public static final String DELETE_ROOM_TYPE_BY_ID_SQL =
            "DELETE FROM typ_pokoju WHERE id = ?";
}
