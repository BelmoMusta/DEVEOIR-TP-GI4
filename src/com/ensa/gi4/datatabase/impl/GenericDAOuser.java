package com.ensa.gi4.datatabase.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public abstract class GenericDAOuser <T> implements InitializingBean {
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() { // from InitializingBean
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

   

    protected T findUser(String query,String name,String password) {
    	try {
        return jdbcTemplate.queryForObject(query, getRowMapper(),name, password);
    	}
    	catch(Exception e){
    	return null;
    }
    }
    protected abstract RowMapper<T> getRowMapper();
}