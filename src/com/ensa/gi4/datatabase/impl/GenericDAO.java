package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.MaterielAllocated;
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
    public void afterPropertiesSet() { // from InitializingBean
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    protected List<T> findAll(String query) {
        return jdbcTemplate.query(query, getRowMapper());
    }

    protected T findOne(String query, int id) {
        try {
            return jdbcTemplate.queryForObject(query, getRowMapper(), id);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    protected abstract RowMapper<T> getRowMapper();

    protected void addMateriel (String query,Materiel materiel){ jdbcTemplate.update(query, materiel.getName(), materiel.getCode(), materiel.isAvailable(), materiel.getQuantity());}

    protected void editMateriel (String query,int id,Materiel materiel){ jdbcTemplate.update(query, materiel.getName(), materiel.getCode(), materiel.getQuantity(),id);}

    protected void editMaterielDisponibility (String query,int id,Materiel materiel){ jdbcTemplate.update(query, !materiel.isAvailable(),id);}

    protected void deleteMateriel (String query,int id){ jdbcTemplate.update(query, id);}

    protected void allocateMateriel(String query1, String query2, Materiel materiel, int userID, int quantity, boolean ifExist, int materielAllocatedQuantity) {
        jdbcTemplate.update(query1, materiel.getQuantity() - quantity, materiel.getId());
        if(!ifExist)
            jdbcTemplate.update(query2, userID, materiel.getId(), quantity);
        else
            jdbcTemplate.update(query2,quantity + materielAllocatedQuantity, userID, materiel.getId());

    }

    protected T findUser(String query, String username, String password)  {
        try {
            return jdbcTemplate.queryForObject(query, getRowMapper(), username,password);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    protected List<T> findAllMaterielAllocated(String query) {
        return jdbcTemplate.query(query, getRowMapper());
    }

    protected List<T> findMaterielAllocatedByUser(String query, int userID) {
        return jdbcTemplate.query(query, getRowMapper(), userID);
    }

    protected T findMaterielAllocatedByUserIDAndMaterielID(String query, int userID, int materielID) {
        try {
            return jdbcTemplate.queryForObject(query, getRowMapper(), userID, materielID);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    protected void returnMaterial(String query1, String query2, Materiel materiel, MaterielAllocated materielAllocated, int quantity){
        jdbcTemplate.update(query1, materiel.getQuantity() + quantity, materiel.getId());
        jdbcTemplate.update(query2,materielAllocated.getQuantity() - quantity, materielAllocated.getId());
    }

}
