package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.RoleDao;
import com.ensa.gi4.modele.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoleDaoImpl extends GenericDAO<Role> implements RoleDao {
    @Override
    public List<Role> findAll() {
        return super.findAll("SELECT * FROM roles");
    }

    @Override
    public Role findOneById(Long id) {
        return super.findOne("SELECT * FROM roles WHERE id=?", id.toString());
    }

    @Override
    public List<Role> findUserRoles(Long userId) {
        final List<Role> roles = new ArrayList<>();
        String query = "SELECT id_role FROM user_roles WHERE id_user=?";
        this.jdbcTemplate.queryForList(query, Integer.class, userId).forEach(roleId -> {
            roles.add(this.findOneById(roleId.longValue()));
        });

        return roles;
    }

    @Override
    protected RowMapper<Role> getRowMapper() {
        return new RoleRowMapper();
    }
}

class RoleRowMapper implements RowMapper<Role>{
    @Override
    public Role mapRow(ResultSet rs, int i) throws SQLException {
        Role role = new Role();
        role.setId(rs.getInt("id"));
        role.setName(rs.getString("name"));
        return role;
    }
}
