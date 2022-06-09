package com.ensa.gi4.database.impl.UserDao;

import com.ensa.gi4.database.api.UserDao;
import com.ensa.gi4.database.impl.GenericDAO;
import com.ensa.gi4.modele.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImpl extends GenericDAO<User> implements UserDao {

    @Override
    protected List<User> findAll(String query) {
        String sql = "SELECT * FROM user";
        return super.findAll(sql);
    }

    @Override
    public User findOne(Long id) {
        String sql = "SELECT * FROM user WHERE id=?";
        return super.findOne(sql, id);
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM user WHERE username=? AND password=?";
        return super.jdbcTemplate.queryForObject(sql, getRowMapper(), username, password);
    }

    @Override
    protected RowMapper<User> getRowMapper() {
        return new UserRowMapper();
    }
}
