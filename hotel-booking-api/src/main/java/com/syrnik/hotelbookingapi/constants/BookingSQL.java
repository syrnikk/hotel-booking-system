package com.syrnik.hotelbookingapi.constants;

public class BookingSQL {
    public static final String INSERT_RESERVATION_SQL =
            "INSERT INTO rezerwacja(uzytkownik_id, przyjazd_data, odjazd_data) VALUES (?, ?, ?)";

    public static final String INSERT_ROOM_RESERVATION_SQL =
            " INSERT INTO pokoj_rezerwacja(rezerwacja_id, pokoj_id) VALUES(?, ?)";

    public static final String SELECT_RESERVATIONS_SQL =
            """
            SELECT r.id, h.nazwa, tp.typ, SUM(tp.cena) AS cena, r.przyjazd_data, r.odjazd_data, COUNT(p.id) as ilosc_pokoji, r.komentarz FROM rezerwacja r INNER JOIN pokoj_rezerwacja pr
            ON r.id = pr.rezerwacja_id INNER JOIN pokoj p ON p.id = pr.pokoj_id
            INNER JOIN typ_pokoju tp ON p.typ_pokoju_id = tp.id INNER JOIN hotel h ON h.id = p.hotel_id
            WHERE r.uzytkownik_id = ? GROUP BY r.id, r.przyjazd_data, r.odjazd_data, r.komentarz, h.nazwa, tp.typ;
            """;

    public static final String ADD_COMMENT_SQL =
            "UPDATE rezerwacja SET komentarz = ? WHERE id = ?";
}
