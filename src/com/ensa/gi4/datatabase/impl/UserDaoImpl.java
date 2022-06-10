package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl  extends GenericDAO<User> implements UserDao {
    @Override
    public User findUser(String username, String password) {
        return super.findUser("SELECT * FROM users WHERE username=? AND password=? ;", username,password);
    }

    @Override
    protected UserRowMapper getRowMapper() { // template method design pattern
        return new UserRowMapper();
    }
}