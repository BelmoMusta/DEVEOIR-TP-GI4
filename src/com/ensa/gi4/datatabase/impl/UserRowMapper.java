package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.appuser.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<AppUser> { @Override
public AppUser mapRow(ResultSet resultSet, int i) throws SQLException {
    AppUser user = new AppUser() { // because it is abstract
    };
    Long id=resultSet.getLong(1);
    String userName = resultSet.getString(2);
    String password = resultSet.getString(3);
    String role = resultSet.getString(4);
   user.setUserName(userName);
    user.setPassword(password);
    user.setAppUserRole(role);
    user.setId(id);
    return user;
}
}
