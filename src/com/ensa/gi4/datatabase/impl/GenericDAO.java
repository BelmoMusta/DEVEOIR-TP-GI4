package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
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
    protected List<T> findAlloueAllUserId(String query,int userId) {
        return jdbcTemplate.query(query, getRowMapper(),userId);
    }

    protected List<T> findAlloueAll(String query) {
        return jdbcTemplate.query(query, getRowMapper());
    }
    protected T findRealPassword(String query, String username) {
        return jdbcTemplate.queryForObject(query, getRowMapper(), username);
    }

    protected void ajouterUser(String query, String username,String password)
    {
        jdbcTemplate.update(query, username,password);
    }

    //fonction pour ajouter un materiel dans DB
    protected void ajouterMateriel(String query, Materiel materiel)
    {
        jdbcTemplate.update(query, materiel.getName(),materiel.getCode(),materiel.getTypeMateriel(), materiel.isDisponible(),materiel.isAllouer());
    }

    //fonction pour chercher un materiel dans DB
    protected T chercherMateriel(String query, Materiel materiel)
    {
        return jdbcTemplate.queryForObject(query,getRowMapper(),materiel.getCode(),materiel.getName());
    }

    protected int supprimerMateriel(String query, Materiel materiel)
    {
        return jdbcTemplate.update(query, materiel.getCode(),materiel.getName());
    }

    protected int supprimerMaterielUser(String query, Materiel materiel)
    {
        return jdbcTemplate.update(query, materiel.getCode());
    }

    protected int modifierDispoMateriel(String query, Materiel materiel)
    {
        return jdbcTemplate.update(query,materiel.isDisponible(),materiel.getCode(), materiel.getName());
    }

    protected int modifierAllouerMateriel(String query, Materiel materiel)
    {
        return jdbcTemplate.update(query,materiel.isAllouer(),materiel.getCode(),materiel.getName());
    }

    protected int modifierMateriel(String query, Materiel materiel,String oldCode)
    {
        return jdbcTemplate.update(query,materiel.getName(),materiel.getTypeMateriel(),materiel.isDisponible(),materiel.isAllouer(),oldCode);
    }

    protected void ajouterMaterielUser(String query, Materiel materiel, User user)
    {
        jdbcTemplate.update(query,user.getIdUser(),materiel.getCode());
    }
    protected abstract RowMapper<T> getRowMapper();
}
