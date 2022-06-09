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

    protected abstract RowMapper<T> getRowMapper();

    protected List<T> login(String query,String name,String password) {
        return jdbcTemplate.query(query, getRowMapper(),name,password);
    }
    protected int addMateriel(String query, String name, String code ){
       return  jdbcTemplate.update(query,name,code );
    }
    protected int deleteMateriel(String deleteQuery,int id){
        return jdbcTemplate.update(deleteQuery,id);
    }
    protected int updateNameMateriel(String query,String nom,int id){
        return jdbcTemplate.update(query,nom,id);


    }
    protected int updateCodeMateriel( String query,String code,int id){
        return jdbcTemplate.update(query,code,id);

    }
    protected int updateIndisponibleMateriel( String query,boolean indisponible,Long id){
        return jdbcTemplate.update(query,indisponible,id);

    }
protected int allouerMateriel(String query,String name,boolean b1,boolean b2, Long id){
        return jdbcTemplate.update(query,name,b1,b2,id);
}
    protected int rendreMateriel(String query,boolean b1,boolean b2, Long id){
        return jdbcTemplate.update(query,b1,b2,id);
    }
}
