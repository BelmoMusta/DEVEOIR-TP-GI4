package com.ensa.gi4.datatabase.impl;

import lombok.RequiredArgsConstructor;
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

    protected T findOne(String query, String arg) {
        try{
            return jdbcTemplate.queryForObject(query, getRowMapper(), arg);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    protected Integer findMaxId(String table){
        return this.jdbcTemplate.queryForObject("SELECT max(id) FROM " + table, Integer.class);
    }



    protected <S> void delete(String query, S arg){jdbcTemplate.update(query, arg.toString());}

    protected abstract RowMapper<T> getRowMapper();
}
