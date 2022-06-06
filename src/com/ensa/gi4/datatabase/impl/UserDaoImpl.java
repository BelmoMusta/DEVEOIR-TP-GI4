package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.RoleDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDaoImpl extends GenericDAO<com.ensa.gi4.modele.User> implements UserDao {
    @Autowired
    RoleDao roleDao;

    @Override
    public Boolean findbyuserinfo(String username, String password) {
        boolean userExists = false;
        String query = "SELECT count(*) FROM USER WHERE username = ? and password = ?" ;
        int count = jdbcTemplate.queryForObject(query,new Object[]{username,password},Integer.class);
        if(count>0){
            userExists=true;
        }
        return userExists;
    }

    @Override
    public User findUserByName(String username) {
        String query = "SELECT * FROM USER WHERE username = ?" ;
        User user = jdbcTemplate.queryForObject(query,getRowMapper(),username);
        return user;
    }

    @Override
    public User findOneById(int id) {
        return super.findOne("SELECT * FROM USER WHERE id=?", id);
    }

    @Override
    public List<User> findAll() {
        return super.findAll("SELECT * FROM USER;");
    }

    @Override
    protected RowMapper<User> getRowMapper() {
        return new UserRowMapper(this.roleDao);
    }
}
