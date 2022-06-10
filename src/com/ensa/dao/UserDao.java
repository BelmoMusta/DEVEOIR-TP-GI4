package com.ensa.dao;

import com.ensa.datatabase.impl.GenericDAO;
import com.ensa.modele.Materiel;
import com.ensa.modele.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
@Component
public class UserDao extends GenericDAO<User> implements DAO {



    @Override
    public List findAll() {
        return null;
    }
public User find(String username, String pass){
    try{return super.jdbcTemplate.queryForObject("SELECT * FROM USER WHERE username=? and password=?", getRowMapper(), username,pass);}
    catch (EmptyResultDataAccessException e) {
        return null;
    }
}
    @Override
    public Materiel findOne(Long id) {
        return null;
    }


    protected RowMapper<User> getRowMapper() {
        return new UserRowMapper();
    }
}
