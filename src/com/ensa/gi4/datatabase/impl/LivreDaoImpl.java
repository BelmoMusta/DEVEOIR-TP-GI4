package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.LivreDao;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Livre;
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
import java.util.List;
import java.util.Map;

@Repository
public class LivreDaoImpl extends GenericDAO<Livre> implements LivreDao {

    @Autowired
    MaterielDao materielDao;

    @Override
    public List<Livre> findAll() {
        return super.findAll("SELECT * FROM materiel;");
    }

    @Override
    public Livre findOne(Integer id) {
        Livre livre =  super.findOne("SELECT * FROM materiel WHERE id=?", id);
        if(livre == null) return null;
        livre.setAuthor(this.getDetails(livre.getId()).get("author").toString());
        return livre;
    }

    @Override
    public void add(Livre livre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreatorFactory psFactory = new PreparedStatementCreatorFactory("INSERT INTO materiel_details(type,author) VALUES ('LIVRE',?)", Types.VARCHAR);
        psFactory.setReturnGeneratedKeys(true);
        PreparedStatementCreator psCreator = psFactory.newPreparedStatementCreator(List.of(livre.getAuthor()));

        this.jdbcTemplate.update(psCreator, keyHolder);

        this.jdbcTemplate.update("INSERT INTO MATERIEL(name,type,stock) VALUES (?,?,?)", livre.getName(), keyHolder.getKey(), livre.getStock());
    }

    @Override
    public void update(Livre livre) {
        this.jdbcTemplate.update("UPDATE materiel_details SET author=? WHERE id=?;", livre.getAuthor(), livre.getId());
        this.jdbcTemplate.update("UPDATE materiel SET name=? WHERE id=?", livre.getName(), livre.getId());
    }

    @Override
    public Map<String, Object> getDetails(Integer id){
        int type_id = materielDao.findOne(id).getType();
        return this.jdbcTemplate.queryForMap("SELECT * FROM materiel_details WHERE id=?", type_id);
    }

    @Override
    protected RowMapper<Livre> getRowMapper() {
        return new LivreRowMapper(this);
    }
}


record LivreRowMapper(LivreDao livreDao) implements RowMapper<Livre> {

    @Override
    public Livre mapRow(ResultSet rs, int i) throws SQLException {
        Livre livre = new Livre();
        int id = rs.getInt("id");
        livre.setId(id);
        livre.setName(rs.getString("name"));
        livre.setAllocated(rs.getInt("allocated"));
        livre.setStock(rs.getInt("stock"));
        livre.setAuthor((String) this.livreDao.getDetails(id).get("author"));

        return livre;
    }
}
