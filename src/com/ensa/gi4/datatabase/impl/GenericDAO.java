package com.ensa.gi4.datatabase.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
        return jdbcTemplate.query(query, getRowMapper());
    }

    protected T findOne(String query, int id) {
    	        return jdbcTemplate.queryForObject(query,getRowMapper(), id);
    }
    protected T findUser(String query, String name,String password) {
        return jdbcTemplate.queryForObject(query,getRowMapper(),name,password);
}
   protected T userExist(String query ,String username , String password) {
	   return jdbcTemplate.queryForObject(query,getRowMapper(),username,password );
   }
   
    protected List<T> findAllAlloue(String query) {
        return jdbcTemplate.query(query, getRowMapper());
    }
    protected void addUser(String query, String username,String password)
    {
        jdbcTemplate.update(query, username,password);
    }
    protected void addMaterial(String query,  String name , String code ,  String type , Boolean disponibility , Boolean allocation )
    {
        jdbcTemplate.update(query, name,code,type,disponibility,allocation);
    }
   protected void userAddMaterial(String query, int idMateriel, int isUser)
    {
        jdbcTemplate.update(query,idMateriel,isUser);
    }

    protected T searchMaterial(String query, Materiel materiel)
    {
        return jdbcTemplate.queryForObject(query,getRowMapper(),materiel.getCode(),materiel.getName());
    }

    protected int deleteMaterial(String query,int id)
    {
        return jdbcTemplate.update(query,id);
    }

    protected int userDeleteMaterial(String query, Materiel materiel)
    {
        return jdbcTemplate.update(query, materiel.getCode());
    }

    protected int modifierMateriel(String query,String newOne,int id)
    {
        return jdbcTemplate.update(query,newOne,id);
    }
    protected int modifierDisponibilité(String query, int id)
    {
        return jdbcTemplate.update(query,id);
    }
    protected int modifierAllocation(String query, int id)
    {
        return jdbcTemplate.update(query,id);
    }
    protected List<T> findAlloueByUserId(String query,int userId) {
        return jdbcTemplate.query(query, getRowMapper(),userId);
    }
    protected T findMaterielAllocatedByUser(String query,int userId, int materielId) {
        return jdbcTemplate.queryForObject(query, getRowMapper(), userId,materielId);
    }
    protected abstract RowMapper<T> getRowMapper();
}
