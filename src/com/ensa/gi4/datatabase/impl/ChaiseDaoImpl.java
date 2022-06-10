package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.ChaiseDao;
import com.ensa.gi4.modele.Chaise;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ChaiseDaoImpl extends GenericDAO<Chaise> implements ChaiseDao {
    @Override
    public List<Chaise> findAll() {
        return jdbcTemplate.query("SELECT * FROM materiel WHERE type=?", getRowMapper(), "chaise");
    }

    @Override
    public Chaise findOne(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM materiel WHERE type=? and id=?", getRowMapper(), "chaise", id);
    }

    @Override
    public void add(Chaise chaise) {
        String sql = String.format("INSERT INTO materiel(name,type,allocated) VALUES (%s,%s,%s)",chaise.getName(),chaise.getType(),false);
        this.jdbcTemplate.update(sql);
    }



    @Override
    protected RowMapper<Chaise> getRowMapper() {
        return new ChaiseRowMapper();
    }
}
