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
    protected long countElementsNotAlloue(String query,String name,String allouer) {
    	try {
   		 return jdbcTemplate.queryForObject(query,Long.class,name,allouer);
       	}
       	catch(Exception e){
       	return 0;
       }
    }
    protected int allouer(String query,String alloue,String name) {
    	try {
   		 return jdbcTemplate.update(query,alloue,name);
    		
       	}
       	catch(Exception e){
       	return 0;
       }
    }
    

    protected abstract RowMapper<T> getRowMapper();
}
