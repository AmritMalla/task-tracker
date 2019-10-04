package com.amt.time_tracker.dao;

import com.amt.time_tracker.model.Login;
import com.amt.time_tracker.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public boolean validate(Login login) throws ClassNotFoundException {
        boolean valid = false;

        Class.forName("org.postgresql.Driver");

        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from users where username = ? and password = ? ")
        ) {
            preparedStatement.setString(1, login.getUsername());
            preparedStatement.setString(2, login.getPassword());
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            valid = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valid;
    }

}
