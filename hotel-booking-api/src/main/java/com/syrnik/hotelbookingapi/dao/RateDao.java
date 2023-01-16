package com.syrnik.hotelbookingapi.dao;

import com.syrnik.hotelbookingapi.constants.RateSQL;
import com.syrnik.hotelbookingapi.model.Hotel;
import com.syrnik.hotelbookingapi.model.Rate;
import com.syrnik.hotelbookingapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class RateDao {
    private final DataSource dataSource;

    public void rate(Rate rate) throws SQLException {
        Rate existingRate = findByHotelAndUserId(rate.getHotel().getId(), rate.getUser().getId());
        if(existingRate == null) {
            try(Connection connection = dataSource.getConnection();
                PreparedStatement addRateStatement = connection.prepareStatement(RateSQL.ADD_RATE_SQL)) {
                addRateStatement.setDouble(1, rate.getRate());
                addRateStatement.setDouble(2, rate.getUser().getId());
                addRateStatement.setDouble(3, rate.getHotel().getId());
                addRateStatement.executeUpdate();
            }
        } else {
            try(Connection connection = dataSource.getConnection();
                PreparedStatement updateRateStatement = connection.prepareStatement(RateSQL.UPDATE_RATE_SQL)) {
                updateRateStatement.setDouble(1, rate.getRate());
                updateRateStatement.setLong(2, existingRate.getId());
                updateRateStatement.executeUpdate();
            }
        }

    }

    public Rate findByHotelAndUserId(Long hotelId, Long userId) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RateSQL.FIND_RATE_BY_USER_AND_HOTEL_SQL)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, hotelId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Rate.builder()
                            .id(resultSet.getLong(1))
                            .rate(resultSet.getDouble(2))
                            .user(User.builder().id(resultSet.getLong(3)).build())
                            .hotel(Hotel.builder().id(resultSet.getLong(4)).build())
                            .build();
                }
            }
        }
        return null;
    }
}
