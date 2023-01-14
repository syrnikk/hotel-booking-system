package com.syrnik.hotelbookingapi.dao;

import com.syrnik.hotelbookingapi.constants.CitySQL;
import com.syrnik.hotelbookingapi.constants.HotelSQL;
import com.syrnik.hotelbookingapi.dto.CityDto;
import com.syrnik.hotelbookingapi.model.Address;
import com.syrnik.hotelbookingapi.model.City;
import com.syrnik.hotelbookingapi.model.Country;
import com.syrnik.hotelbookingapi.model.Hotel;
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
public class HotelDao {
    private final DataSource dataSource;
    private final AddressDao addressDao;

    public List<Hotel> findByCityName(String name) throws SQLException {
        List<Hotel> hotels = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HotelSQL.SELECT_HOTEL_BY_CITY_NAME_SQL)) {
            preparedStatement.setString(1, name);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()) {
                    City city = City.builder()
                            .id(resultSet.getLong(11))
                            .name(resultSet.getString(12))
                            .build();
                    Address address = Address.builder()
                            .city(city)
                            .street(resultSet.getString(8))
                            .number(resultSet.getString(9))
                            .postalCode(resultSet.getString(10))
                            .build();
                    Hotel hotel = Hotel.builder()
                            .id(resultSet.getLong(1))
                            .name(resultSet.getString(2))
                            .phone(resultSet.getString(4))
                            .title(resultSet.getString(5))
                            .description(resultSet.getString(6))
                            .stars(resultSet.getInt(7))
                            .address(address)
                            .build();
                    hotels.add(hotel);
                }
            }
        }
        return hotels;
    }

    public List<Hotel> find() throws SQLException {
        List<Hotel> hotels = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HotelSQL.SELECT_HOTEL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()){
            while(resultSet.next()) {
                City city = City.builder()
                        .id(resultSet.getLong(11))
                        .name(resultSet.getString(12))
                        .build();
                Address address = Address.builder()
                        .city(city)
                        .street(resultSet.getString(8))
                        .number(resultSet.getString(9))
                        .postalCode(resultSet.getString(10))
                        .build();
                Hotel hotel = Hotel.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .phone(resultSet.getString(4))
                        .title(resultSet.getString(5))
                        .description(resultSet.getString(6))
                        .stars(resultSet.getInt(7))
                        .address(address)
                        .build();
                hotels.add(hotel);
            }
        }
        return hotels;
    }

    public Hotel findById(Long hotelId) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HotelSQL.SELECT_HOTEL_BY_ID_SQL)) {
            preparedStatement.setLong(1, hotelId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Country country = Country.builder()
                            .id(resultSet.getLong(13))
                            .name(resultSet.getString(14))
                            .build();
                    City city = City.builder()
                            .id(resultSet.getLong(11))
                            .name(resultSet.getString(12))
                            .country(country)
                            .build();
                    Address address = Address.builder()
                            .id(resultSet.getLong(3))
                            .city(city)
                            .street(resultSet.getString(8))
                            .number(resultSet.getString(9))
                            .postalCode(resultSet.getString(10))
                            .build();
                    Hotel hotel = Hotel.builder()
                            .id(resultSet.getLong(1))
                            .name(resultSet.getString(2))
                            .phone(resultSet.getString(4))
                            .title(resultSet.getString(5))
                            .description(resultSet.getString(6))
                            .stars(resultSet.getInt(7))
                            .address(address)
                            .build();
                    return hotel;
                }
            }
        }
        return null;
    }

    public void save(Hotel hotel) throws SQLException {
        Long addressId = addressDao.save(hotel.getAddress());
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HotelSQL.INSERT_HOTEL_SQL)) {
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setLong(2, addressId);
            preparedStatement.setString(3, hotel.getPhone());
            preparedStatement.setString(4, hotel.getTitle());
            preparedStatement.setString(5, hotel.getDescription());
            preparedStatement.setInt(6, hotel.getStars());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteById(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HotelSQL.DELETE_HOTEL_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public void updateById(Long id, Hotel hotel) throws SQLException {
        addressDao.updateById(hotel.getAddress().getId(), hotel.getAddress());
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HotelSQL.UPDATE_HOTEL_SQL)) {
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setLong(2, hotel.getAddress().getId());
            preparedStatement.setString(3, hotel.getPhone());
            preparedStatement.setString(4, hotel.getTitle());
            preparedStatement.setString(5, hotel.getDescription());
            preparedStatement.setInt(6, hotel.getStars());
            preparedStatement.setLong(7, id);
            preparedStatement.executeUpdate();
        }
    }
}
