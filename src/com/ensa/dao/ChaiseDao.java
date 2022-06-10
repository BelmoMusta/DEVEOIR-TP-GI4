package com.ensa.dao;

import com.ensa.datatabase.impl.GenericDAO;
import com.ensa.modele.Chaise;
import com.ensa.modele.Livre;
import com.ensa.modele.Materiel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;
import java.util.ArrayList;
        import java.util.List;
@Component
public class ChaiseDao extends GenericDAO<Chaise> implements DAO, InitializingBean {

    List<Livre> livres = new ArrayList<Livre>();
    List<Chaise> chaises = new ArrayList<Chaise>();



    public List<Chaise> findAll() {
        return super.findAll("SELECT * FROM CHAISE;");
    }

    @Override
    public Chaise findOne(Long id) {
        return super.findOne("SELECT * FROM CHAISE WHERE ID=?;", id);
    }

public Chaise findByCode(String code){try{ return super.findByCode("SELECT * FROM CHAISE WHERE CODE=?;", code);}catch (EmptyResultDataAccessException e) {
    return null;
}}

    public void ajouter(Chaise c){

        super.jdbcTemplate.update("insert into chaise (name, code,prix,hauteur) values (?, ?,?,?)",c.getName(),c.getCode(),c.getPrix(),c.getHauteur());}
public void supprimer(String code){ super.jdbcTemplate.update("DELETE FROM CHAISE WHERE CODE=?;",code);}
public void updateorder(String code,String a){
    super.jdbcTemplate.update("update chaise set isordered=? where code=?",a,code);
}
    public void modifier(Chaise c,String code){
        super.jdbcTemplate.update("update chaise set code=?,name=?,prix=?,hauteur=? where code=?",c.getCode(),c.getName(),c.getPrix(),c.getHauteur(),code);
    }
    @Override
    protected RowMapper<Chaise> getRowMapper() {
        return new ChaiseRowMapper();
    }
    public Boolean isordered(String code){
      String isordered=  (String) super.jdbcTemplate.queryForObject("SELECT isordered FROM CHAISE WHERE CODE=?;",new Object[] { code }, String.class);
      if(isordered.equals("false")) return false;
      else return true;
    }
}


