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


    protected T executeQuery(String query) {
        try {
            return jdbcTemplate.queryForObject(query, getRowMapper());
        }catch(Exception e){
            return null;
        }
    }

    protected abstract RowMapper<T> getRowMapper();

    protected int addMateriel(String query,Object [] args) {
        return jdbcTemplate.update(query,args);
    }

    protected int deleteMateriel(String query,Long id) {
        return jdbcTemplate.update(query,id);
    }

    protected int materielInavailable(String query,Long id) {
        return jdbcTemplate.update(query,id);
    }
    protected int updateMateriel(String query,String name , String code ,String type ,Boolean disponibilite, Long id) {
        return jdbcTemplate.update(query,name,code,type,disponibilite,id);
    }
    protected int allocateMateriel(String query,Long idMateriel,String nomMateriel, Long idUtilisateur,String nomUser, int dureeAllocation ) {
        return jdbcTemplate.update(query, idMateriel,nomMateriel,idUtilisateur,nomUser,dureeAllocation);
    }

    protected int changerQuantite(String query, int nvQuantite, long idMateriel ) {
        return jdbcTemplate.update(query, nvQuantite, idMateriel);
    }

    protected int DisponibiliteMateriel(String query, boolean disponibilite,Long idMateriel){
        return jdbcTemplate.update(query,disponibilite, idMateriel);
    };
    protected int AllocationMateriel(String query, boolean disponibilite,Long idMateriel){
        return jdbcTemplate.update(query,disponibilite, idMateriel);
    };
    protected int returnMateriel(String query,Long idMateriel, Long idUser){
        return jdbcTemplate.update(query, idMateriel,idUser);
    }


}
