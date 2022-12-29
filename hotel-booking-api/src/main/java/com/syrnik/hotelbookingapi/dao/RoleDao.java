package com.syrnik.hotelbookingapi.dao;

import com.syrnik.hotelbookingapi.constants.RoleSQL;
import com.syrnik.hotelbookingapi.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class RoleDao {
    private final DataSource dataSource;

    public Long getRoleByName(String name) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RoleSQL.SELECT_ROLE_ID_BY_NAME_SQL)) {
            preparedStatement.setString(1, name);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    return resultSet.getLong(1);
                } else {
                    throw new SQLException("There is no role with name: " + name);
                }
            }
        }
    }

    public void save(Role role) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RoleSQL.INSERT_ROLE_SQL)) {
            preparedStatement.setString(1, role.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
