package com.syrnik.hotelbookingapi.dao;

import com.syrnik.hotelbookingapi.constants.CitySQL;
import com.syrnik.hotelbookingapi.constants.RoomSQL;
import com.syrnik.hotelbookingapi.dto.AvailableRoomDto;
import com.syrnik.hotelbookingapi.model.*;
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

    public List<Room> find() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RoomSQL.SELECT_ROOMS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()) {
                RoomType roomType = RoomType.builder()
                        .id(resultSet.getLong(6))
                        .type(resultSet.getString(7))
                        .build();
                Hotel hotel = Hotel.builder()
                        .id(resultSet.getLong(4))
                        .name(resultSet.getString(5))
                        .build();
                Room room = Room.builder()
                        .id(resultSet.getLong(1))
                        .roomNumber(resultSet.getString(2))
                        .floor(resultSet.getInt(3))
                        .roomType(roomType)
                        .hotel(hotel)
                        .build();
                rooms.add(room);
            }
        }
        return rooms;
    }

    public void save(Room room) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RoomSQL.INSERT_ROOM_SQL)) {
            preparedStatement.setLong(1, room.getRoomType().getId());
            preparedStatement.setLong(2, room.getHotel().getId());
            preparedStatement.setString(3, room.getRoomNumber());
            preparedStatement.setInt(4, room.getFloor());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteById(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RoomSQL.DELETE_ROOM_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public Room findById(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RoomSQL.SELECT_ROOM_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            try( ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    RoomType roomType = RoomType.builder()
                            .id(resultSet.getLong(6))
                            .type(resultSet.getString(7))
                            .build();
                    Hotel hotel = Hotel.builder()
                            .id(resultSet.getLong(4))
                            .name(resultSet.getString(5))
                            .build();
                    Room room = Room.builder()
                            .id(resultSet.getLong(1))
                            .roomNumber(resultSet.getString(2))
                            .floor(resultSet.getInt(3))
                            .roomType(roomType)
                            .hotel(hotel)
                            .build();
                    return room;
                }
            }
        }
        return null;
    }

    public void updateById(Long id, Room room) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RoomSQL.UPDATE_ROOM_SQL)) {
            preparedStatement.setLong(1, room.getRoomType().getId());
            preparedStatement.setLong(2, room.getHotel().getId());
            preparedStatement.setString(3, room.getRoomNumber());
            preparedStatement.setInt(4, room.getFloor());
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
        }
    }
}
