package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDAO<User> implements UserDao {
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND mdp =?";
        return super.jdbcTemplate.queryForObject(query,getRowMapper(),username,password);
    }
    @Override
    public User findOne(Long id){
        String sql = "SELECT * FROM user WHERE id_user=?";
        return super.findOne(sql,id);
    }

    @Override
    protected RowMapper<User> getRowMapper() {
        return new UserRowMapper();
    }
}
