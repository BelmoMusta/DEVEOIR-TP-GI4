package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper  implements RowMapper<User> {

    @Override

    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        User user=new User() ;
        user.setId(resultSet.getInt(1));
        user.setUsername(resultSet.getString("userName"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(resultSet.getString("role"));
        return user;

    }
}
