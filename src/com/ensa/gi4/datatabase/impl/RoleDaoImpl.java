package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.RoleDao;
import com.ensa.gi4.modele.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class RoleDaoImpl extends GenericDAO<Role> implements RoleDao {
    @Override
    public List<Role> findAll() {
        return  super.findAll("SELECT * FROM role");
    }

    @Override
    public List<Role> findUserAllRoles(int idUser) {
        List<Role> allRoles = new ArrayList<>();
        String sql = "SELECT id_role FROM user_roles WHERE id_user=?";
        this.jdbcTemplate.queryForList(sql,Integer.class,idUser).forEach(id->{
            allRoles.add(this.findOneById(id));
        });
        return allRoles ;
    }

    @Override
    public Role findOneById(int id) {
        return super.findOne("SELECT * FROM role WHERE id=?", id);
    }

    @Override
    protected RowMapper<Role> getRowMapper() {
        return new RoleRowMapper();
    }
}
