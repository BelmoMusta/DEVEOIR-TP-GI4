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
        return jdbcTemplate.query(query, getRowMapper());
    }

    protected T findOne(String query, String code) {
        return jdbcTemplate.queryForObject(query, getRowMapper(), code);
    }
    protected int addOne(String query,String code, String name) {
		return jdbcTemplate.update(query,code,name);
    	
    }
    protected int deleteOne(String query,String code) {
 		return jdbcTemplate.update(query,code);
     	
     }
    protected int checkAvailability(String query,String code) {
		return jdbcTemplate.update(query,code);
    	
    }
    protected int updateName(String query,String code,String name) {
		return jdbcTemplate.update(query,code,name);
    	
    }
    protected int allocateMateriel(String query,int userId,String code) {
		return jdbcTemplate.update(query,userId,code);
    	
    }
    protected List<T> findAllocatedMaterielG(String query,int userId) {
        return jdbcTemplate.query(query, getRowMapper(),userId);
    }
    

    protected abstract RowMapper<T> getRowMapper();
}
