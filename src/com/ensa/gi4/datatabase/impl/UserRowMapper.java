package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.RoleDao;
import com.ensa.gi4.modele.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper  implements RowMapper<User> {
    private RoleDao roleDao ;

    public UserRowMapper(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRoles(roleDao.findUserAllRoles(user.getId()));
        return user;
    }
}
