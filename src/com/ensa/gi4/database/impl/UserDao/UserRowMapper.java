package com.ensa.gi4.database.impl.UserDao;

import com.ensa.gi4.modele.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        String username = resultSet.getString("username");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        return new User(id, username, firstName, lastName);
    }
}
