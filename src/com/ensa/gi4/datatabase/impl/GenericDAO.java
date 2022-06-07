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

    protected T findOne(String query, Long id) {

        return jdbcTemplate.queryForObject(query, getRowMapper(), id);
    }

    protected int deleteOne(String query, Long id) {

        return jdbcTemplate.update(query, id);
    }

    protected int addOne(String query, String name, String code, String type, Long stock, Boolean disponibility) {

        return jdbcTemplate.update(query, name, code, type, stock, disponibility);

    }

    protected abstract RowMapper<T> getRowMapper();
}
