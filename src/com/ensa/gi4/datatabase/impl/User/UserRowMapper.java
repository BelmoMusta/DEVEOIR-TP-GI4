package com.ensa.gi4.datatabase.impl.User;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        User user = new User() {

        };
        Long id = resultSet.getLong(1);
        String userName = resultSet.getString(2);
        String password = resultSet.getString(3);
        String role = resultSet.getString(4);
        user.setId(id);
        user.setUserName(userName);
        user.setPassword(password);
        user.setRole(role);

        return user;
    }
}