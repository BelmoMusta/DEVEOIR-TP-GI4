package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.ChaiseDao;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.utils.EntityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Repository
public class ChaiseDaoImpl extends GenericDAO<Chaise> implements ChaiseDao {

    @Value("${materiel.chaise.input.fields}")
    private String fields;
    @Autowired
    private EntityUtils entityUtils;

    @Override
    public List<Chaise> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM materiel WHERE type=?", getRowMapper(), "CHAISE");
    }

    @Override
    public Chaise findOne(Long id) {
        return super.jdbcTemplate.queryForObject("SELECT * FROM materiel WHERE type=? and id=?", getRowMapper(), "CHAISE", id);
    }

    @Override
    public Chaise save(Chaise chaise) {
        String query = String.format("INSERT INTO materiel(%s) VALUES (%s)", fields, fields.replaceAll("\\w+", "?"));
        this.jdbcTemplate.update(query, entityUtils.extractInputFields(chaise));
        chaise.setId(this.findMaxId("materiel"));
        return chaise;
    }

    @Override
    protected RowMapper<Chaise> getRowMapper() {
        return new ChaiseRowMapper(entityUtils);
    }
}

@RequiredArgsConstructor
class ChaiseRowMapper implements RowMapper<Chaise>{
    final EntityUtils entityUtils;
    @Override
    public Chaise mapRow(ResultSet rs, int i) throws SQLException {
        Chaise chaise = new Chaise();
        entityUtils.populateFromResultSet(chaise, rs);
        return chaise;
    }
}