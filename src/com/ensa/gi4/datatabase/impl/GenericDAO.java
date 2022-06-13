package com.ensa.gi4.datatabase.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

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
        return (List<T>) jdbcTemplate.query(query, getRowMapper());
    }

    protected T findOne(String query, String code) {
        return (T) jdbcTemplate.queryForObject(query, getRowMapper(), code);
    }

    //Add Material
    protected void AddMaterial(String query,Materiel m) {
        this.jdbcTemplate.update(query,m.getCode(),m.getName(),m.getDisponible());
    }


    protected void RemoveMateriel(String query, String code) {
        this.jdbcTemplate.update(query,code);
    }



    protected void UpdateMateriel(String query,String code1,String code2) {
        this.jdbcTemplate.update(query,code1,code2);
    }

    //Allouer materiel
    protected void allouerMateriel(String query,String name, String username) {
        this.jdbcTemplate.update(query,name,username);
    }

    protected void ModifierDisponibilite(String query,Boolean dispo,String code) {
        this.jdbcTemplate.update(query,dispo,code);
    }

    protected void supprimerMaterielAlloue(String query,String code) {
        this.jdbcTemplate.update(query,code);
    }

    protected T findUser(String query,String username,String password) {
        try{
            return this.jdbcTemplate.queryForObject(query,getRowMapper(), username,password);
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }



    protected String findRole(String query,String username) {
        //	Object[] parameters = new Object[] {username};
        return (String) this.jdbcTemplate.queryForObject(query,String.class,username);
    }

    protected List<Allocation> findListAlloue(String query){
        return (List<Allocation>) jdbcTemplate.query(query,getRowMapperAllocation());
    }

    protected List<Allocation> findListAlloueByName(String query,String username){
        return (List<Allocation>) jdbcTemplate.query(query,getRowMapperAllocation(),username);
    }

    protected Allocation findAllocate(String query,String code) {
        return jdbcTemplate.queryForObject(query, getRowMapperAllocation(),code);
    }

    protected abstract RowMapper<T> getRowMapper();
    protected abstract RowMapper<Allocation> getRowMapperAllocation();

}
