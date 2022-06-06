package com.ensa.gi4.datatabase.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

import java.sql.SQLException;
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
    protected void insererOuUpdate(String sql ) {   	

    	this.jdbcTemplate.update(sql);
    }
    
    protected abstract RowMapper<T> getRowMapper();
}
