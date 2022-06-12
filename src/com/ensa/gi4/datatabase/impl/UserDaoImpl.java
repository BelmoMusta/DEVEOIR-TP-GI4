package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends GenericDAO<User> implements UserDao {

  /*  @Override
    public List<User> loginUser(User user) {
        List<User> _user = super.loginUser("SELECT *FROM user where username=? and password =?",user.getUsername(),user.getPassword());
        return _user;
    }*/

    @Override
    public User findUser(String username, String password) {
       return super.findUser("SELECT *FROM user where username=? and password =?",username,password);
    }

    @Override
    protected RowMapper<User> getRowMapper() {
        // template method design pattern
        return new UserRowMapper();
    }

}
