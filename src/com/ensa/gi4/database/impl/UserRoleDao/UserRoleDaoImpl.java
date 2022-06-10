package com.ensa.gi4.database.impl.UserRoleDao;

import com.ensa.gi4.database.api.UserRoleDao;
import com.ensa.gi4.database.impl.GenericDAO;
import com.ensa.gi4.modele.UserRole;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRoleDaoImpl extends GenericDAO<UserRole> implements UserRoleDao {
    @Override
    public List<UserRole> findUserRoles(Long userId) {
        String sql = "SELECT * FROM user_roles WHERE user_id=?";
        return super.jdbcTemplate.query(sql, getRowMapper(), userId);
    }

    @Override
    protected RowMapper<UserRole> getRowMapper() {
        return new UserRoleRowMapper();
    }
}
