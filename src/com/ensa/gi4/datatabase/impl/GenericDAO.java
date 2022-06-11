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

    protected int insertMateriel(String query, String nom, String code) {
        return jdbcTemplate.update(query, nom,code,1,0);
    }

    protected int updateMateriel(String query, String newCode,Long id) {
        return jdbcTemplate.update(query,newCode,id);
    }

    protected int deleteMateriel(String query, Long id) {
        return jdbcTemplate.update(query,id);
    }

    protected List<T> ListeParchacun(String query,Long id) {
        return jdbcTemplate.query(query, getRowMapper(),id);
    }
    protected List<T> ListeParLui(String query,Long id) {
        return jdbcTemplate.query(query, getRowMapper(),id);
    }
    protected abstract RowMapper<T> getRowMapper();

    protected List<T> loginUser(String query,String name,String password) {
        return jdbcTemplate.query(query, getRowMapper(),name,password);
    }

    protected int marquerDisponible(String query,int dispo, Long id) {
        return jdbcTemplate.update(query,dispo,id);
    }
    protected int allouéMateriel(String query,int alloué,Long employee, Long id) {
        return jdbcTemplate.update(query,alloué,employee,id);
    }
    protected int rendreMateriel(String query,int alloué,Long employee, Long id) {
        return jdbcTemplate.update(query,alloué,employee,id);
    }
}
