package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.ChaiseDao;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Chaise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class ChaiseDaoImpl extends GenericDAO<Chaise> implements ChaiseDao {

    @Autowired
    MaterielDao materielDao;

    @Override
    public List<Chaise> findAll() {
        return super.findAll("SELECT * FROM materiel;");
    }

    @Override
    public Chaise findOne(Integer id) {
        Chaise chaise=  super.findOne("SELECT * FROM materiel WHERE id=?", id);
        if(chaise == null) return null;
        chaise.setMadeof(this.getDetails(chaise.getId()).get("madeof").toString());
        return chaise;
    }

    @Override
    public void add(Chaise chaise) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreatorFactory psFactory = new PreparedStatementCreatorFactory("INSERT INTO materiel_details(type,madeof) VALUES ('CHAISE',?)", Types.VARCHAR);
        PreparedStatementCreator psCreator = psFactory.newPreparedStatementCreator(Arrays.asList(chaise.getMadeof()));

        this.jdbcTemplate.update(psCreator, keyHolder);

        this.jdbcTemplate.update("INSERT INTO MATERIEL(name,type,stock) VALUES (?,?,?,?)", chaise.getName(), keyHolder.getKey(), chaise.getStock());
    }

    @Override
    public void update(Chaise chaise) {
        this.jdbcTemplate.update("UPDATE materiel_details SET madeof=? WHERE id=?;", chaise.getMadeof(), chaise.getId());
        this.jdbcTemplate.update("UPDATE materiel SET name=? WHERE id=?", chaise.getName(), chaise.getId());
    }

    @Override
    public Map getDetails(Integer id) {
        int type_id = materielDao.findOne(id).getType();
        return this.jdbcTemplate.queryForMap("SELECT * FROM materiel_details WHERE id=?", type_id);
    }

    @Override
    protected RowMapper<Chaise> getRowMapper() {
        return new ChaiseeRowMapper(this);
    }
}

record ChaiseeRowMapper(ChaiseDao chaiseDao) implements RowMapper<Chaise> {

    @Override
    public Chaise mapRow(ResultSet rs, int i) throws SQLException {
        Chaise chaise = new Chaise();
        int id = rs.getInt("id");
        chaise.setId(id);
        chaise.setName(rs.getString("name"));
        chaise.setAllocated(rs.getInt("allocated"));
        chaise.setStock(rs.getInt("stock"));
        chaise.setMadeof((String) this.chaiseDao.getDetails(id).get("madeof"));

        return chaise;
    }
}