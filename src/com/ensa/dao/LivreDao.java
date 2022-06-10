package com.ensa.dao;

import com.ensa.datatabase.impl.GenericDAO;
import com.ensa.modele.Chaise;
import com.ensa.modele.Livre;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class LivreDao extends GenericDAO<Livre> implements DAO{
    List<Livre> livres = new ArrayList<Livre>();
    List<Chaise> chaises = new ArrayList<Chaise>();


    public List<Livre> findAll() {
        return super.findAll("SELECT * FROM LIVRE;");
    }

    @Override
    public Livre findOne(Long id) {
        return super.findOne("SELECT * FROM LIVRE WHERE ID=?;", id);
    }

    public Livre findByCode(String code){try{ return super.findByCode("SELECT * FROM LIVRE WHERE CODE=?;", code);}catch (EmptyResultDataAccessException e) {
        return null;
    }}

    public void ajouter(Livre c){

        super.jdbcTemplate.update("insert into livre (name, code,prix,auteur) values (?, ?,?,?)",c.getName(),c.getCode(),c.getPrix(),c.getAuteur());}
    public void supprimer(String code){ super.jdbcTemplate.update("DELETE FROM livre WHERE CODE=?;",code);}
    public void updateorder(String code,String a){
        super.jdbcTemplate.update("update livre set isordered=? where code=?",a,code);
    }

    public Boolean isordered(String code){
        String isordered=  (String) super.jdbcTemplate.queryForObject("SELECT isordered FROM LIVRE WHERE CODE=?;",new Object[] { code }, String.class);
        if(isordered.equals("false")) return false;
        else return true;
    }
    public void modifier(Livre l,String code){
        super.jdbcTemplate.update("update livre set code=?,name=?,prix=?,auteur=? where code=?",l.getCode(),l.getName(),l.getPrix(),l.getAuteur(),code);
    }




    @Override
    protected RowMapper<Livre> getRowMapper() {
        return new LivreRowMapper();
    }
}
