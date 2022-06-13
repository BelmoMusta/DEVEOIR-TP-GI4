package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int arg1) throws SQLException {
        User user = new User() {
        };

        user.setUserId(resultSet.getLong("USERID"));
        user.setName(resultSet.getString("NAME"));
        user.setPassword(resultSet.getString("PASSWORD"));
        user.setRole(resultSet.getString("ROLE"));
        return user;

} }