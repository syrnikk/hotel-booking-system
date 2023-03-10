package com.syrnik.hotelbookingapi.constants;

public class AddressSQL {
    public static final String INSERT_ADDRESS_SQL =
            "INSERT INTO adres(miasto_id, ulica, numer, kod_pocztowy) VALUES (?, ?, ?, ?)";

    public static final String UPDATE_ADDRESS_SQL =
            "UPDATE adres SET miasto_id = ?, ulica = ?, numer = ?, kod_pocztowy = ? WHERE id = ?";
}
