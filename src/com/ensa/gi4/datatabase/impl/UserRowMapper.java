package com.ensa.gi4.datatabase.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.User;
import com.ensa.gi4.modele.User;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User User = new User() { // because it is abstract
        };

        String name = resultSet.getString(2);
        //String name_ = resultSet.getString("NAME");
        //String password_ = resultSet.getString("password");
        String password = resultSet.getString(3);
        String role= resultSet.getString(4);
        long id = resultSet.getLong(1);
        User.setId(id);
      
        User.setPassword(password);
        User.setName(name);
        User.setRole(role);

        return User;
    }
}