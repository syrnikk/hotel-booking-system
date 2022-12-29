package com.syrnik.hotelbookingapi.constants;

public class UserSQL {
    public static final String SELECT_USER_BY_EMAIL_SQL =
            "SELECT id, imie, nazwisko, email, haslo, telefon, aktywny FROM uzytkownik WHERE email = ?";

    public static final String SELECT_USER_AUTHORITIES_SQL =
            "SELECT r.nazwa FROM rola r inner join uzytkownik_rola ur ON r.id = ur.rola_id WHERE ur.uzytkownik_id = ?";

    public static final String INSERT_USER_SQL =
            "INSERT INTO uzytkownik(imie, nazwisko, email, haslo, telefon) VALUES (?, ?, ?, ?, ?)";

    public static final String INSERT_AUTHORITIES_SQL =
            "INSERT INTO uzytkownik_rola(uzytkownik_id, rola_id) VALUES (?, ?)";
}
