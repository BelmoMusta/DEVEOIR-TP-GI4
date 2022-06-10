package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.LivreDao;
import com.ensa.gi4.modele.Livre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class LivreDaoImpl extends GenericDAO<Livre> implements LivreDao {
    @Override
    public List<Livre> findAll() {
        return jdbcTemplate.query("SELECT * FROM materiel WHERE type=?", getRowMapper(), "livre");
    }

    @Override
    public Livre findOne(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM materiel WHERE type=? and id=?", getRowMapper(), "livre", id);
    }

    @Override
    public void add(Livre livre) {
        String sql = String.format("INSERT INTO materiel(name,type,allocated) VALUES (%s,%s,%s)",livre.getName(),livre.getType(),false);
        this.jdbcTemplate.update(sql);

    }



    @Override
    protected RowMapper<Livre> getRowMapper() {
        return new LivreRowMapper();
    }
}
