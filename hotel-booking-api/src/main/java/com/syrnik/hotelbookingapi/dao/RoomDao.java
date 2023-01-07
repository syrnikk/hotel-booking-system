package com.syrnik.hotelbookingapi.dao;

import com.syrnik.hotelbookingapi.constants.RoomSQL;
import com.syrnik.hotelbookingapi.dto.AvailableRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoomDao {
    private final DataSource dataSource;

    public List<AvailableRoomDto> findAvailableRoomsGroupByType(Long hotelId, LocalDate arrivalDate, LocalDate departureDate) throws SQLException {
        List<AvailableRoomDto> availableRooms = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RoomSQL.SELECT_AVAILABLE_ROOMS_GROUP_BY_TYPE_SQL)) {
            preparedStatement.setLong(1, hotelId);
            preparedStatement.setDate(2, Date.valueOf(arrivalDate));
            preparedStatement.setDate(3, Date.valueOf(departureDate));
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()) {
                    AvailableRoomDto availableRoom = AvailableRoomDto.builder()
                            .id(resultSet.getLong(1))
                            .type(resultSet.getString(2))
                            .amountOfPeople(resultSet.getInt(3))
                            .price(resultSet.getBigDecimal(4))
                            .description(resultSet.getString(5))
                            .amountOfAvailableRooms(resultSet.getInt(6))
                            .build();
                    availableRooms.add(availableRoom);
                }
            }
            return availableRooms;
        }
    }

    public List<Long> findAvailableRoomsIdByType(Long hotelId, LocalDate arrivalDate, LocalDate departureDate, Long roomTypeId) throws SQLException {
        List<Long> availableRooms = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RoomSQL.SELECT_AVAILABLE_ROOMS_BY_ROOM_TYPE_SQL)) {
            preparedStatement.setLong(1, hotelId);
            preparedStatement.setDate(2, Date.valueOf(arrivalDate));
            preparedStatement.setDate(3, Date.valueOf(departureDate));
            preparedStatement.setLong(4, roomTypeId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()) {
                    availableRooms.add(resultSet.getLong(1));
                }
            }
            return availableRooms;
        }
    }
}
