package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Material;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;

import java.util.List;

@Repository
public class UserDaoImpl extends GenericDAO<User> implements UserDao{

    @Override
    public List<User> getUsers()  {
        return super.getUsers("SELECT * FROM USERS;");
    }

    @Override
    public User getUser(long userId) {
        return super.findOne("SELECT * FROM USERS WHERE ID=?;", userId);
    }

    @Override

    public User Login(String name, String password) {
        String query ="SELECT * FROM USERS WHERE NAME=? AND PASSWORD=?";
        return super.jdbcTemplate.queryForObject(query, getRowMapper(), name, password);
    }

    @Override
    protected RowMapper<User> getRowMapper() {
        return new UserRowMapper();
    }
}