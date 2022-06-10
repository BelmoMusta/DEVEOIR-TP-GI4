package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getInt("id"));
        role.setName(resultSet.getString("name"));
        return role;
    }
}
