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
    public void afterPropertiesSet() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    protected List<T> findAll(String query) {
        return jdbcTemplate.query(query, getRowMapper());
    }
    protected List<T> findAllById(String query,int id){
        return jdbcTemplate.query(query, getRowMapper(),id);
    }

    protected T findOne(String query, Long id) {
        return jdbcTemplate.queryForObject(query, getRowMapper(), id);
    }
    protected boolean findBool(String query,int id){
        return jdbcTemplate.queryForObject(query,Boolean.class,id);
    }

    protected int addOne(String query,String name,String code,String type,Boolean disponibility,Boolean allocation){
        return jdbcTemplate.update(query,name,code,type,disponibility,allocation);
    }
    protected int deleteOne(String query,int id){
        return jdbcTemplate.update(query,id);
    }
    protected int modifyOne(String query,String nouveau,int id)
    {
        return jdbcTemplate.update(query,nouveau,id);
    }
    protected int makeUnavailable(String query,long id){
        return jdbcTemplate.update(query,id);
    }

    protected T findAllUser(String query,String name,String password){
        return jdbcTemplate.queryForObject(query, getRowMapper(),name,password);
    }
    protected  int findIdByName(String query,String name){
        return jdbcTemplate.queryForObject(query,Integer.class,name);
    }
    protected  int modifyAllocationId(String query,int id){
        return jdbcTemplate.update(query,id);
    }
    protected  int modifyAllocation(String query,String  name){
        return jdbcTemplate.update(query,name);
    }
    protected int addMatAll(String query,int idUser,int idMateriel,int quantity,int nb_days){
        return jdbcTemplate.update(query,idUser,idMateriel,quantity,nb_days);
    }
    protected int addUser(String query,String username,String password){
        return jdbcTemplate.update(query,username,password);
    }
    protected int  findIdById(String query,int id){
        return jdbcTemplate.queryForObject(query,Integer.class,id);
    }


    protected abstract RowMapper<T> getRowMapper();

}
