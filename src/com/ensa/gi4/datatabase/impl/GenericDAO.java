package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.rmi.CORBA.Util;
import javax.sql.DataSource;
import java.util.List;

public abstract class GenericDAO<T> implements InitializingBean {
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() { // from InitializingBean
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    protected List<T> findAll(String query) {
        return jdbcTemplate.query(query, getRowMapper());
    }

    protected T findOne(String query, String code) {

        return jdbcTemplate.queryForObject(query, getRowMapper(), code);
    }

    protected  void add(String query, Materiel materiel) {this.jdbcTemplate.update(query, materiel.getName(), materiel.getType(), materiel.getAvailable(), materiel.getCode());}
    protected abstract RowMapper<T> getRowMapper();

    protected int delete(String query, Materiel materiel) {
        return this.jdbcTemplate.update(query,materiel.getName(), materiel.getCode());
    }

    protected int update(String query, Materiel materiel) {

        return this.jdbcTemplate.update(query,materiel.getName(), materiel.getType(), materiel.getAvailable(), materiel.getCode());
    }

    protected int updateAv(String query, Materiel materiel){
        return this.jdbcTemplate.update(query,materiel.getAvailable(), materiel.getCode());

    }

    protected T findUtilisateur(String query, String username) {
        return this.jdbcTemplate.queryForObject(query, getRowMapper(), username);}

    protected int allouer(String query, Utilisateur utilisateur, Materiel materiel, String duree){
        return this.jdbcTemplate.update(query, utilisateur.getIdUser(),materiel.getCode(),  duree);
    }
    protected int rendre(String query, Utilisateur utilisteur, Materiel materiel){
        return this.jdbcTemplate.update(query, utilisteur.getIdUser(), materiel.getCode());
    }

    protected int countAlloue(String query) {
        return jdbcTemplate.queryForObject(query,Integer.class);
    }
    protected List<T> matsAlloue(String query, int idUser){
        return this.jdbcTemplate.query(query, getRowMapper(), idUser);
    }

    protected int countLivre(String query, String s) {
        return jdbcTemplate.queryForObject(query,Integer.class, s);
    }
    protected int countChaise(String query, String s) {
        return jdbcTemplate.queryForObject(query,Integer.class, s);
    }

    protected int updatePassword(String query, Utilisateur utilisateur){
        return this.jdbcTemplate.update(query,utilisateur.getPassword(), utilisateur.getIdUser());
    }
}
