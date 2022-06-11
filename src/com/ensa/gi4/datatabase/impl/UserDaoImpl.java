package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public class UserDaoImpl extends GenericDAO<User>implements UserDao {


    @Override
    public List<User> userList() {
         return super.findAll("SELECT * FROM USERS;");
    }

    @Override
    public List<User> listRoles(User user) {
        return super.login("SELECT * FROM USERS WHERE NAME=? AND PASSWORD=?;",user.getName(), user.getPassword());
    }


    @Override
    protected RowMapper<User> getRowMapper() {
        return new UserRowMapper();
    }

}
