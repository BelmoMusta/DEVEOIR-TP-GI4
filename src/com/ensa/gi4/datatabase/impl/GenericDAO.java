package com.ensa.gi4.datatabase.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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

    protected T findOne(String query, Long id) {
        return jdbcTemplate.queryForObject(query, getRowMapper(), id);
    }

    protected abstract RowMapper<T> getRowMapper();



    protected int ajouterNouveauMateriel(String query, String nom, String code) {
        return jdbcTemplate.update(query,nom,code);
    }

    protected int supprimerMateriel(String query, Long id) {

        return jdbcTemplate.update(query,id);
    }

    protected int modifierMateriel(String query, String code, String newCode) {
        return jdbcTemplate.update(query,code,newCode);
    }

    protected int marquerIndisponible(String query,Long dispo, Long id) {

        return jdbcTemplate.update(query,dispo,id);
    }

    protected int allouerMateriel(String query, Long i, Long ii, Long id) {

        return jdbcTemplate.update(query,i,ii,id);
    }

    protected List<T> login(String query,String name,String password) {
        return jdbcTemplate.query(query, getRowMapper(),name,password);
    }

    protected List<T> allouerMaterielIdUser(String query, String username) {
        return jdbcTemplate.query(query,getRowMapper(),username);
    }

    protected int rendreMateriel(String query, Long i, String ii, Long id) {

        return jdbcTemplate.update(query,i,ii,id);
    }
    protected List<T> afficherMaterielLuiMeme(String query, Long id) {
        return jdbcTemplate.query(query, getRowMapper(),id);
    }


}
