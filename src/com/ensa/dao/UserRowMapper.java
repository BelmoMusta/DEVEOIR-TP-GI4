package com.ensa.dao;

import com.ensa.modele.Chaise;
import com.ensa.modele.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user=new User();
        String name=resultSet.getString("Name");
        String username=resultSet.getString("username");
        String pass=resultSet.getString("password");
        Boolean isadmin=resultSet.getBoolean("isadmin");
        user.setIsadmin(isadmin);user.setUsername(username);user.setName(name);user.setPassword(pass);
        return user;
    }
}
