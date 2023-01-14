package com.syrnik.hotelbookingapi.dao;

import com.syrnik.hotelbookingapi.constants.CountrySQL;
import com.syrnik.hotelbookingapi.constants.RoomTypeSQL;
import com.syrnik.hotelbookingapi.model.Country;
import com.syrnik.hotelbookingapi.model.RoomType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoomTypeDao {
    private final DataSource dataSource;

    public List<RoomType> find() throws SQLException {
        List<RoomType> roomTypes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RoomTypeSQL.SELECT_ROOM_TYPES_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()) {
                roomTypes.add(RoomType.builder()
                                .id(resultSet.getLong(1))
                                .type(resultSet.getString(2))
                                .amountOfPeople(resultSet.getInt(3))
                                .price(resultSet.getBigDecimal(4))
                                .description(resultSet.getString(5))
                                .build());
            }
        }
        return roomTypes;
    }

    public void save(RoomType roomType) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RoomTypeSQL.INSERT_ROOM_TYPE_SQL)) {
            preparedStatement.setString(1, roomType.getType());
            preparedStatement.setInt(2, roomType.getAmountOfPeople());
            preparedStatement.setBigDecimal(3, roomType.getPrice());
            preparedStatement.setString(4, roomType.getDescription());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteById(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RoomTypeSQL.DELETE_ROOM_TYPE_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public RoomType findById(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RoomTypeSQL.SELECT_ROOM_TYPE_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            try( ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    return RoomType.builder()
                            .id(resultSet.getLong(1))
                            .type(resultSet.getString(2))
                            .amountOfPeople(resultSet.getInt(3))
                            .price(resultSet.getBigDecimal(4))
                            .description(resultSet.getString(5))
                            .build();
                }
            }
        }
        return null;
    }

    public void updateById(Long id, RoomType roomType) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RoomTypeSQL.UPDATE_ROOM_TYPE_SQL)) {
            preparedStatement.setString(1, roomType.getType());
            preparedStatement.setInt(2, roomType.getAmountOfPeople());
            preparedStatement.setBigDecimal(3, roomType.getPrice());
            preparedStatement.setString(4, roomType.getDescription());
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
        }
    }
}
