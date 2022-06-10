package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Allocation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public abstract class GenericDAO<T> implements InitializingBean {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    protected abstract RowMapper<T> getRowMapper();

    protected List<T> getAllMateriels(String query) {
        return jdbcTemplate.query(query, getRowMapper());
    }

    protected T getMaterielByName(String query, String name) {
        try {
            return jdbcTemplate.queryForObject(query, getRowMapper(), name);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    protected List<T> getAllAllocations(String query) {
        return jdbcTemplate.query(query, getRowMapper());
    }

    protected List<T> getAllocationsByUser(String query, int userID) {
        return jdbcTemplate.query(query, getRowMapper(), userID);
    }

    protected void addMateriel (String query,Materiel materiel) {
        jdbcTemplate.update(query, materiel.getName(), materiel.getCode(), materiel.isAvailable(), materiel.getQuantity());
    }

    protected void deleteMateriel (String query,String nom) {
        jdbcTemplate.update(query, nom);
    }

    protected void editMaterielName (String query, String oldName, String newName) {
        jdbcTemplate.update(query, newName, oldName);
    }

    protected void editMaterielCode (String query, String name, String code) {
        jdbcTemplate.update(query, code, name);
    }

    protected void editMaterielAvailability (String query, String name, boolean availability) {
        jdbcTemplate.update(query, availability, name);
    }

    protected void editMaterielQuantity (String query, String name, int quantity) {
        jdbcTemplate.update(query, quantity, name);
    }

    protected T findUser(String query, String username, String password) {
        try {
            return jdbcTemplate.queryForObject(query, getRowMapper(), username,password);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    protected T ifMaterielIsAllocatedByUser(String query, int userID, int materielID) {
        try {
            return jdbcTemplate.queryForObject(query, getRowMapper(), userID, materielID);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    protected T getAllocationByMateriel(String query, int materielID) {
        try {
            return jdbcTemplate.queryForObject(query, getRowMapper(), materielID);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    protected void allocateMateriel(String query1, String query2, Materiel materiel, int userID, int quantity, boolean ifExist, int materielAllocatedQuantity) {
        jdbcTemplate.update(query1, materiel.getQuantity() - quantity, materiel.getId());
        if(!ifExist) {
            jdbcTemplate.update(query2, userID, materiel.getId(), quantity);
        } else {
            jdbcTemplate.update(query2, materielAllocatedQuantity + quantity, userID, materiel.getId());
        }
    }

    protected void returnMaterial(String query1, String query2, Materiel materiel, Allocation allocation, int quantity, int userID) {
        jdbcTemplate.update(query1, materiel.getQuantity() + quantity, materiel.getId());
        if (allocation.getQuantity() - quantity == 0)
            jdbcTemplate.update(query2, userID, materiel.getId());
        else
            jdbcTemplate.update(query2,allocation.getQuantity() - quantity, userID, materiel.getId());
    }

}
