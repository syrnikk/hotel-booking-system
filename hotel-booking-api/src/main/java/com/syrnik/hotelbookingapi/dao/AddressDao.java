package com.syrnik.hotelbookingapi.dao;

import com.syrnik.hotelbookingapi.constants.AddressSQL;
import com.syrnik.hotelbookingapi.constants.HotelSQL;
import com.syrnik.hotelbookingapi.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
@RequiredArgsConstructor
public class AddressDao {
    private final DataSource dataSource;

    public Long save(Address address) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(AddressSQL.INSERT_ADDRESS_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, address.getCity().getId());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getNumber());
            preparedStatement.setString(4, address.getPostalCode());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
        }
        return null;
    }

    public void updateById(Long id, Address address) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(AddressSQL.UPDATE_ADDRESS_SQL)) {
            preparedStatement.setLong(1, address.getCity().getId());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getNumber());
            preparedStatement.setString(4, address.getPostalCode());
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
        }
    }
}
