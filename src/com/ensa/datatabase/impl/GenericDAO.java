package com.ensa.datatabase.impl;

import com.ensa.modele.Materiel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public abstract class GenericDAO<T> implements InitializingBean {
    @Autowired
    private DataSource dataSource;
    public JdbcTemplate jdbcTemplate;

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
    protected T findByCode(String query, String code) {
        return jdbcTemplate.queryForObject(query, getRowMapper(), code);
    }
    protected List<T> findByCodeList(String query, String code) {
        return jdbcTemplate.query(query, getRowMapper(), code);
    }

    protected abstract RowMapper<T> getRowMapper();
}
