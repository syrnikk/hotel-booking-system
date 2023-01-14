package com.syrnik.hotelbookingapi.dao;

import com.syrnik.hotelbookingapi.constants.BookingSQL;
import com.syrnik.hotelbookingapi.dto.BookingRequest;
import com.syrnik.hotelbookingapi.dto.ReservationDetailsDto;
import com.syrnik.hotelbookingapi.model.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookingDao {
    private final DataSource dataSource;
    private final RoomDao roomDao;

    public void makeReservation(Long userId, BookingRequest bookingRequest) throws SQLException {
        Long reservationId = null;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement reservationStatement = connection.prepareStatement(BookingSQL.INSERT_RESERVATION_SQL, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement roomReservationStatement = connection.prepareStatement(BookingSQL.INSERT_ROOM_RESERVATION_SQL)) {
                reservationStatement.setLong(1, userId);
                reservationStatement.setDate(2, Date.valueOf(bookingRequest.getStartDate()));
                reservationStatement.setDate(3, Date.valueOf(bookingRequest.getEndDate()));
                reservationStatement.executeUpdate();
                try (ResultSet generatedKeys = reservationStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        reservationId = generatedKeys.getLong(1);
                    }
                }

                List<Long> availableRoomIds = roomDao.findAvailableRoomsIdByType(bookingRequest.getHotelId(),
                        bookingRequest.getStartDate(), bookingRequest.getEndDate(), bookingRequest.getRoomTypeId());

                for (int i = 0; i < bookingRequest.getRoomAmount(); i++) {
                    roomReservationStatement.setLong(1, reservationId);
                    roomReservationStatement.setLong(2, availableRoomIds.get(i));
                    roomReservationStatement.addBatch();
                }
                roomReservationStatement.executeBatch();

                connection.commit();
            } catch (SQLException exception) {
                connection.rollback();
                throw exception;
            }
        }
    }

    public List<ReservationDetailsDto> getReservations(Long userId) throws SQLException{
        List<ReservationDetailsDto> reservationDetailsDtoList = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(BookingSQL.SELECT_RESERVATIONS_SQL)) {
            ps.setLong(1, userId);
            try(ResultSet resultSet = ps.executeQuery()) {
                while(resultSet.next()) {
                    ReservationDetailsDto reservationDetailsDto = ReservationDetailsDto.builder()
                            .reservationId(resultSet.getLong(1))
                            .hotelName(resultSet.getString(2))
                            .roomType(resultSet.getString(3))
                            .price(resultSet.getBigDecimal(4))
                            .startDate(resultSet.getDate(5).toLocalDate())
                            .endDate(resultSet.getDate(6).toLocalDate())
                            .roomAmount(resultSet.getInt(7))
                            .comment(resultSet.getString(8))
                            .build();
                    reservationDetailsDtoList.add(reservationDetailsDto);
                }
            }
        }
        return reservationDetailsDtoList;
    }

    public void addComment(Long id, Booking booking) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(BookingSQL.ADD_COMMENT_SQL)) {
            preparedStatement.setString(1, booking.getComment());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        }
    }
}
