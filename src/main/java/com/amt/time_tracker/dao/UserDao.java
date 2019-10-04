package com.amt.time_tracker.dao;

import com.amt.time_tracker.model.User;
import com.amt.time_tracker.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public int registerUser(User user) {
        String INSERT_USERS_SQL = "INSERT INTO users"
                + " (first_name, last_name, username, password)"
                + " VALUES (?,?,?,?);";
        int result = 0;
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement statement = conn.prepareStatement(INSERT_USERS_SQL)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());

            System.out.println(statement);

            result = statement.executeUpdate();

        } catch (SQLException e) {
            JdbcUtils.printSQLException(e);
        }
        return result;
    }

    public User getUserByUsername(String username) {
        User user = null;
        String USER_SELECT_SQL = " SELECT * FROM users WHERE username = ?";
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(USER_SELECT_SQL)) {
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User.UserBuilder builder = User.builder();
                builder.id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .username(username);
                user = builder.build();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Long getUserIdByUsername(String username) {
        Long userId = null;
        String USER_SELECT_SQL = " SELECT * FROM users WHERE username = ?";
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(USER_SELECT_SQL)) {
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userId = resultSet.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

}
