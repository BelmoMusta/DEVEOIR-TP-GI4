package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDaoImpl  extends GenericDAO<User>  implements UserDao {

    @Override
    public List<User> findUser(User user) {
        List<User> users =  super.signUp("SELECT * FROM users where name=? AND password=?;",user.getName(),user.getPassword());
      return users;
    }

    @Override
    protected RowMapper<User> getRowMapper() {
        return new  UserRowMapper();
    }

}
