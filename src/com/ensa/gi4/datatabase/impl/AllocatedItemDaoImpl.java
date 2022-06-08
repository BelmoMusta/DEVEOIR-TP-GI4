package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.AllocatedItemDao;
import com.ensa.gi4.modele.AllocatedItem;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AllocatedItemDaoImpl extends GenericDAO<AllocatedItem> implements AllocatedItemDao {
    @Override
    public List<AllocatedItem> findAll() {
        return super.findAll("SELECT * FROM AllocatedItems;");
    }

    @Override
    public List<AllocatedItem> findByUser(int id_user) {
        String query = "SELECT * FROM AllocatedItems WHERE id_user=?";
        return jdbcTemplate.query(query, getRowMapper(), id_user);
    }

    @Override
    public void addItem(int id_user, int id_materiel) {
        String query = "INSERT INTO AllocatedItems(id_user,id_materiel) VALUES(?,?)";
        this.jdbcTemplate.update(query,id_user,id_materiel);
    }

    @Override
    public void deleteItem(int id_user, int id_materiel) {
        String query ="DELETE FROM AllocatedItems WHERE id_user=? AND id_materiel=?";
        this.jdbcTemplate.update(query,id_user,id_materiel);
    }

    @Override
    protected RowMapper<AllocatedItem> getRowMapper() {
        return new AllocatedItemRowMapper();
    }
}
