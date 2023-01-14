package com.syrnik.hotelbookingapi.dao;

import com.syrnik.hotelbookingapi.constants.CitySQL;
import com.syrnik.hotelbookingapi.constants.CountrySQL;
import com.syrnik.hotelbookingapi.dto.CityDto;
import com.syrnik.hotelbookingapi.model.City;
import com.syrnik.hotelbookingapi.model.Country;
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
public class CityDao {
    private final DataSource dataSource;

    public List<City> findByCountryName(String name) throws SQLException {
        List<City> cities = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CitySQL.SELECT_CITIES_BY_COUNTRY_NAME_SQL)) {
            preparedStatement.setString(1, name);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()) {
                    cities.add(City.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .build());
                }
            }
        }
        return cities;
    }

    public List<City> find() throws SQLException {
        List<City> cities = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CitySQL.SELECT_CITIES_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()) {
                Country country = Country.builder()
                        .id(resultSet.getLong(3))
                        .name(resultSet.getString(4))
                        .build();
                City city = City.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .country(country)
                        .build();
                cities.add(city);
            }
        }
        return cities;
    }

    public void deleteById(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CitySQL.DELETE_CITY_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public void save(City city) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CitySQL.INSERT_CITY_SQL)) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setLong(2, city.getCountry().getId());
            preparedStatement.executeUpdate();
        }
    }

    public City findById(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CitySQL.SELECT_CITY_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            try( ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    Country country = Country.builder()
                            .id(resultSet.getLong(3))
                            .name(resultSet.getString(4))
                            .build();
                    City city = City.builder()
                            .id(resultSet.getLong(1))
                            .name(resultSet.getString(2))
                            .country(country)
                            .build();
                    return city;
                }
            }
        }
        return null;
    }

    public void updateById(Long id, City city) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CitySQL.UPDATE_CITY_SQL)) {
            preparedStatement.setLong(1, city.getCountry().getId());
            preparedStatement.setString(2, city.getName());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        }
    }
}
