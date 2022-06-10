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
    protected JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() { // from InitializingBean
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    protected List<T> findAll(String query) {
        return jdbcTemplate.query(query, getRowMapper());
    }

     protected <S> T findOne(String query, S... cols) {
        try{
            return jdbcTemplate.queryForObject(query, getRowMapper(), cols);
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    protected <S> void delete(String query, S col){
        this.jdbcTemplate.update(query, col);
    }



    protected abstract RowMapper<T> getRowMapper();
}
