package com.ensa.gi4.database.impl.RoleDao;

import com.ensa.gi4.database.api.RoleDao;
import com.ensa.gi4.database.impl.GenericDAO;
import com.ensa.gi4.modele.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl extends GenericDAO<Role> implements RoleDao {
    @Override
    public List<Role> findAll() {
        String sql = "SELECT * FROM roles";
        return super.findAll(sql);
    }

    @Override
    public Role fineOne(Long id) {
        String sql = "SELECT * FROM roles WHERE id=?";
        return super.findOne(sql, id);
    }

    @Override
    protected RowMapper<Role> getRowMapper() {
        return new RoleRowMapper();
    }
}
