package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.LivreDao;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.utils.EntityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class LivreDaoImpl extends GenericDAO<Livre> implements LivreDao {

    @Value("${materiel.livre.input.fields}")
    private String fields;
    @Autowired
    private EntityUtils entityUtils;



    @Override
    public List<Livre> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM materiel WHERE type=?", getRowMapper(), "LIVRE");
    }

    @Override
    public Livre findOne(Integer id) {
        return super.jdbcTemplate.queryForObject("SELECT * FROM materiel WHERE type=? and id=?", getRowMapper(), "LIVRE", id);
    }

    @Override
    public Livre save(Livre livre) {
        String query = String.format("INSERT INTO materiel(%s) VALUES (%s)", fields, fields.replaceAll("\\w+", "?"));
        this.jdbcTemplate.update(query, entityUtils.extractInputFields(livre));
        livre.setId(this.findMaxId("materiel"));
        return livre;
    }

    @Override
    public Integer findMaxId(){
        return super.findMaxId("Materiel");
    }




    @Override
    protected RowMapper<Livre> getRowMapper() {
        return new LivreRowMapper(entityUtils);
    }
}

@RequiredArgsConstructor
class LivreRowMapper implements RowMapper<Livre>{
    final EntityUtils entityUtils;
    @Override
    public Livre mapRow(ResultSet rs, int i) throws SQLException {
        Livre livre = new Livre();
        entityUtils.populateFromResultSet(livre, rs);
        return livre;
    }
}