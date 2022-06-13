package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
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
    protected List<T> getUsers(String query) {
        return jdbcTemplate.query(query, getRowMapper());
    }
    protected T findOne(String query, Long materialId) {
        return jdbcTemplate.queryForObject(query, getRowMapper(), materialId);
    }
    protected T getUser(String query, Long userId) {
        return jdbcTemplate.queryForObject(query, getRowMapper(), userId);
    }

    protected abstract RowMapper<T> getRowMapper();






}
