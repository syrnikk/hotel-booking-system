package com.syrnik.hotelbookingapi.dao;

import com.syrnik.hotelbookingapi.constants.CountrySQL;
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
public class CountryDao {
    private final DataSource dataSource;

    public List<Country> find() throws SQLException {
        List<Country> countries = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CountrySQL.SELECT_COUNTRIES_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()) {
                countries.add(new Country(resultSet.getLong(1), resultSet.getString(2)));
            }
        }
        return countries;
    }

    public void save(Country country) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CountrySQL.INSERT_COUNTRY_SQL)) {
            preparedStatement.setString(1, country.getName());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteById(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CountrySQL.DELETE_COUNTRY_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
