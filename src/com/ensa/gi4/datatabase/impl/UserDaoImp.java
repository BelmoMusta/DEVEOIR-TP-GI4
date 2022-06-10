package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDaoImp extends GenericDAO<User> implements UserDao {
    @Autowired
    private RoleDao roleDao;

    @Override
    protected RowMapper<User> getRowMapper() {
        return new UserRowMapper(roleDao);
    }

    @Override
    public User findOneById(Integer id) {
        return super.findOne("SELECT * FROM user WHERE id=?", id);
    }

    @Override
    public User findOneByUsername(String username) {
        return super.findOne("SELECT * FROM user WHERE username=?", username);
    }
}


record UserRowMapper(RoleDao roleDao) implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();
        int userId = rs.getInt("id");
        user.setId(userId);
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRoles(roleDao.getUserRoles(userId));
        return user;
    }
}
