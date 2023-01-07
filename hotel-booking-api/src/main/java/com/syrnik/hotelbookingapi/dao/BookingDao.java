package com.syrnik.hotelbookingapi.dao;

import com.syrnik.hotelbookingapi.constants.BookingSQL;
import com.syrnik.hotelbookingapi.constants.UserSQL;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class BookingDao {
    private final DataSource dataSource;

    public void makeReservation(Long userId, LocalDate startDate, LocalDate endDate, Long roomId) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(BookingSQL.MAKE_BOOKING_SQL, Statement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, userId);
                ps.setDate(2, Date.valueOf(startDate));
                ps.setDate(3, Date.valueOf(endDate));
                ps.setLong(4, roomId);
                ps.executeUpdate();
        }
    }
}
