package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RoleDao extends GenericDAO<Role>{

    public Role findOne(Integer id){
        return super.findOne("SELECT * FROM roles WHERE id=?", id);
    }

    public List<Role> getUserRoles(Integer userId){
        List<Integer> roles = this.jdbcTemplate.queryForList("SELECT id_role FROM user_roles WHERE id_user=?", Integer.class, userId);
        return roles.stream().map(this::findOne).toList();
    }

    @Override
    protected RowMapper<Role> getRowMapper() {
        return new RoleRowMapper();
    }
}

class RoleRowMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet rs, int i) throws SQLException {
        return new Role(rs.getInt("id"), rs.getString("name"));
    }
}
