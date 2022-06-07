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
       
    	try {
    		 return jdbcTemplate.query(query, getRowMapper());
        	}
        	catch(Exception e){
        	return null;
        }
    }

    protected T findOne(String query, Long id) {
        
    	try {
    		return jdbcTemplate.queryForObject(query, getRowMapper(), id);
       	}
       	catch(Exception e){
       	return null;
       }
    }
    protected int ajouterOne(String query,String name,String code) {
    	try {
      		 return jdbcTemplate.update(query,name,code);
       		
          	}
          	catch(Exception e){
          	return 0;
          }
    	
    }
  
    protected int allouer(String query,Long alloue,String name) {
    	try {
   		 return jdbcTemplate.update(query,alloue,name);
    		
       	}
       	catch(Exception e){
       	return 0;
       }
    }
    protected int rendreMateriel(String query,Long idUser,Long idMateriel) {
    	try {
   		 return jdbcTemplate.update(query,idUser,idMateriel);
    		
       	}
       	catch(Exception e){
       	return 0;
       }
    }
    protected int supprimerOne(String query, Long id) {
    	try {
    		 return jdbcTemplate.update(query,id);
       	}
       	catch(Exception e){
       	return 0;
       }
    }
    protected int modifierOne(String query,String name,String code, Long id) {
    	try {
    		 return jdbcTemplate.update(query,name,code,id);
       	}
       	catch(Exception e){
       	return 0;
       }
    }
    protected abstract RowMapper<T> getRowMapper();
}
