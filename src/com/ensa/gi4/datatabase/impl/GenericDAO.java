package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Materiel;
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
    protected int add(String query,String nom,String code) {
        return jdbcTemplate.update(query,nom,code);
    }
    protected int delet(String query,String code) {
        return jdbcTemplate.update(query,code);
    }
    protected int update(String query, String code, String newCode) {
        return jdbcTemplate.update(query,code,newCode);
    }
    protected int  allouer(String query, int alloue ,Long userId,Long matId) {return jdbcTemplate.update(query,alloue,userId,matId);

    }



    protected abstract RowMapper<T> getRowMapper();

    protected List<T> signUp(String query, String name, String password) {
        return jdbcTemplate.query(query, getRowMapper(), name, password);
    }


    protected int marquerDisponible(String query,int dispo, Long id) {
        return jdbcTemplate.update(query,dispo,id);
    }

    protected int epuise(String query, int epuise, Long id) {
        return jdbcTemplate.update(query,epuise,id);
    }
}
