package com.syrnik.hotelbookingapi.dao;

import com.syrnik.hotelbookingapi.constants.UserSQL;
import com.syrnik.hotelbookingapi.model.Role;
import com.syrnik.hotelbookingapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDao {
    private final DataSource dataSource;
    private final RoleDao roleDao;

    public Optional<User> findByEmail(String email) {
        User user = new User();
        List<Role> roles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(UserSQL.SELECT_USER_BY_EMAIL_SQL);
             PreparedStatement authoritiesStatement = connection.prepareStatement(UserSQL.SELECT_USER_AUTHORITIES_SQL)) {
            userStatement.setString(1, email);
            try (ResultSet resultSet = userStatement.executeQuery()) {
                if (resultSet.next()) {
                    user.setId(resultSet.getLong(1));
                    user.setName(resultSet.getString(2));
                    user.setLastName(resultSet.getString(3));
                    user.setEmail(resultSet.getString(4));
                    user.setPassword(resultSet.getString(5));
                    user.setPhone(resultSet.getString(6));
                    user.setActive(resultSet.getBoolean(7));
                } else {
                    return Optional.empty();
                }
            }
            authoritiesStatement.setLong(1, user.getId());
            try (ResultSet resultSet = authoritiesStatement.executeQuery()) {
                while (resultSet.next()) {
                    roles.add(new Role(resultSet.getString(1)));
                }
            }
            user.setRoles(roles);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.of(user);
    }

    public void save(User user) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement userStatement = connection.prepareStatement(UserSQL.INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement authoritiesStatement = connection.prepareStatement(UserSQL.INSERT_AUTHORITIES_SQL)) {
                connection.setAutoCommit(false);

                userStatement.setString(1, user.getName());
                userStatement.setString(2, user.getLastName());
                userStatement.setString(3, user.getEmail());
                userStatement.setString(4, user.getPassword());
                userStatement.setString(5, user.getPhone());

                int affectedRows = userStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }
                try (ResultSet generatedKeys = userStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getLong(1));
                    }
                }
                for (var role : user.getRoles()) {
                    authoritiesStatement.setLong(1, user.getId());
                    authoritiesStatement.setLong(2, roleDao.getRoleByName(role.getName()));
                    authoritiesStatement.addBatch();
                }
                authoritiesStatement.executeBatch();
                connection.commit();
            } catch (SQLException exception) {
                connection.rollback();
                throw exception;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
