package com.ensa.gi4.datatabase.impl;

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
    
    protected int update(String query,Object[] objects) {
        return jdbcTemplate.update(query,objects);
    }
    

    protected List<T> findAll(String query, Object[] objects) {
        return jdbcTemplate.query(query, getRowMapper(),objects);
    }

    protected T findOne(String query, Object[] objects) {
    	try {
        return jdbcTemplate.queryForObject(query, getRowMapper(),objects);
    	} catch (EmptyResultDataAccessException e) {
			return null;
		}
    }
   
    
   
   
  
    protected abstract RowMapper<T> getRowMapper();

	


}
