package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Material;
import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();

        user.setUsername(resultSet.getString("username"));
        user.setUser_id(resultSet.getInt("user_id"));
        user.setRole_id(resultSet.getInt("role_id"));
        user.setHashed_password(resultSet.getString("hashed_password"));
        user.setRole(new Role(resultSet.getInt("role_id"),resultSet.getString("name")));

        return user;
    }
}