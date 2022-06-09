package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.modele.UserRoles;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user=new User();{}

        String role = resultSet.getString(2);

        String name = resultSet.getString(3);
        String password = resultSet.getString(4);

        user.setRole(role);
        user.setName(name);
        user.setPassword(password);

        return user;
    }
}
