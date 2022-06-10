package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Materiel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

public abstract class GenericDAO<T> implements InitializingBean {
    @Autowired
    private DataSource dataSource;
    JdbcTemplate jdbcTemplate;

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
    public void allocateMaterialDAO(String query, Long uid, Long mid, Date date){
        jdbcTemplate.update(query, uid,mid,date);
    }
    public int allocateMaterialDAO(String query,  Long mid){
        return jdbcTemplate.update(query,mid);
    }
    public void returnMaterialDAO(String query,Long id){
        jdbcTemplate.update(query, id);
    }
    public int getReturnMaterialDAOChange(String query,Long id){
        return jdbcTemplate.update(query, id);
    }
    public List<T> findMaterialAllocatedDAO(String query){

        return jdbcTemplate.query(query, getRowMapper());
    }
    public List<T> findMaterialAllocatedDAO(String query,Long id){

        return jdbcTemplate.query(query, getRowMapper(), id);
    }
    //Materiel rechercheParIdDAO(String code);
    public void addMaterialDAO(String query,String name,String code,Integer stock,Boolean disponibility){
         jdbcTemplate.update(query, name,code,stock,disponibility);
    }
    public void deleteMaterialDAO(String query,Long id){
       // jdbcTemplate.update(query, getRowMapper(), id);
        jdbcTemplate.update(query,id);
    }
    public void updateMaterialDAO(String query, String name,String code,Integer stock,Long id){
        jdbcTemplate.update(query,name,code,stock,id);
    }
    public void updateMaterialDAO(String query, Materiel materiel){
        jdbcTemplate.update(query,materiel.getName(),materiel.getCode(),materiel.getStock(),materiel.getId());
    }
    public void makeItUnavailable(String query,Long id) {
        jdbcTemplate.update(query,id);
    }
    protected int cheeck(String query,Long id){
        return jdbcTemplate.update(query,id);
    }
    protected void signUpDAO(String query,String name, String password,String role){
        jdbcTemplate.update(query,name,password,role);
    }
    protected abstract RowMapper<T> getRowMapper();
}
