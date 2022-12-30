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
import java.util.ArrayList;
import java.util.List;

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

    public void save(Role role) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RoleSQL.INSERT_ROLE_SQL)) {
            preparedStatement.setString(1, role.getName());
            preparedStatement.executeUpdate();
        }
    }

    public List<Role> find() throws SQLException {
        List<Role> roles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RoleSQL.SELECT_ROLES_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()) {
                roles.add(new Role(resultSet.getLong(1), resultSet.getString(2)));
            }
        }
        return roles;
    }

    public void deleteById(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RoleSQL.DELETE_ROLE_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
