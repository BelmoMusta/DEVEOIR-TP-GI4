package com.ensa.gi4.datatabase.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        return jdbcTemplate.queryForObject(query, getRowMapper(), id);
    }

    protected abstract RowMapper<T> getRowMapper();

	protected T Auth(String query) {
		try {
			return jdbcTemplate.queryForObject(query, getRowMapper());
		}catch(DataAccessException e) {
			return null;
		}
	}
	
    protected void execute(String query) {
        jdbcTemplate.execute(query);
   }
}
