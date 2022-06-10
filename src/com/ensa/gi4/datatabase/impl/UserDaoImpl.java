package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends GenericDAO<User> implements UserDao {


    @Override
    public User findUser(String userName, String password) {

        String query = "SELECT * FROM USER WHERE username=? AND password=?;";
        return super.findUser(query, userName, password);

    }

    @Override
    public List<User> findAllUsers() {

        String query = "SELECT * FROM USER;";
        return super.findAllUser(query);

    }

    @Override
    protected RowMapper<User> getRowMapper() {

        return new UserRowMapper();

    }
}
