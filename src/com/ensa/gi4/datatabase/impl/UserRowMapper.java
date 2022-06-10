package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User() { // because it is abstract
        };
        String username_ = resultSet.getString("USERNAME");
        String password_ = resultSet.getString("PASSWORD");
        user.setUsername(username_);
        user.setPassword(password_);
        return user;
    }
}
