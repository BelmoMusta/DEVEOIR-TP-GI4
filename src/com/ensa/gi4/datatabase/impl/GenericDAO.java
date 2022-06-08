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
    protected  JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() { // from InitializingBean
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    protected List<T> findAll(String query) {
        return jdbcTemplate.query(query, getRowMapper());
    }

    protected T findOne(String query, Long id) {
    	try {
            return jdbcTemplate.queryForObject(query, getRowMapper(), id);
        	} catch (Exception e) {
        		return null;
        	}
    }
    protected T executeQuery(String query) {
    	try {
        return jdbcTemplate.queryForObject(query, getRowMapper());
    	} catch (Exception e) {
    		return null;
    	}
    }
    protected String getRole(String query,String name) {
    	
    	try {
    		return (String) jdbcTemplate.queryForObject(query,new Object[] {name}, String.class);
    	}catch(Exception e) {
    		return null;
    	}
    }
    protected int inseretUpdateDelete(String sql ) {   	

    	return this.jdbcTemplate.update(sql);
    }

    protected int count(String sql ) {
    	int i = this.jdbcTemplate.queryForObject(sql, Integer.class);
    	return i;
    }

    protected abstract RowMapper<T> getRowMapper();
}
