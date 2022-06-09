package com.ensa.gi4.database.impl.UserRoleDao;

import com.ensa.gi4.modele.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleRowMapper implements RowMapper<UserRole> {

    @Override
    public UserRole mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        Long userId = resultSet.getLong("user_id");
        Long roleId = resultSet.getLong("user_id");
        return new UserRole(id, userId, roleId);
    }
}
