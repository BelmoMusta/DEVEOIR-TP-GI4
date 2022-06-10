package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.RoleDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserDaoImp extends GenericDAO<User> implements UserDao {

    private final static String ADD_USER = "INSERT INTO USER(username,password,email) VALUES(?,?,?)";
    @Autowired
    RoleDao roleDao;

    @Override
    public List<User> findAll() {
        return super.findAll("SELECT * FROM USER;");
    }

    @Override
    public User findOneById(Long id) {
        return super.findOne("SELECT * FROM USER WHERE id=?", id.toString());
    }

    @Override
    public User findOneByUsername(String username) {
        return super.findOne("SELECT * FROM USER WHERE username=?", username);
    }

    @Override
    public User add(User u) {
        this.jdbcTemplate.update(ADD_USER, u.getUsername(), u.getPassword(), u.getEmail());
        User newUser = this.findOneByUsername(u.getUsername());
        this.addRole(newUser, this.roleDao.findOneById(2L));
        return newUser;
    }


    @Override
    public void delete(Integer id) {
        super.delete("DELETE FROM USER WHERE id=", id);
    }

    @Override
    public void delete(String username) {
        super.delete("DELETE FROM USER WHERE username=", username);
    }

    @Override
    public void lock(Integer id, boolean value) {
        this.jdbcTemplate.update("UPDATE user SET locked=? WHERE id=?", value,id);
    }

    @Override
    protected RowMapper<User> getRowMapper() {
        return new UserRowMapper(this.roleDao);
    }

    public void addRole(User user, Role role){
        boolean alreadyHasRole = this.roleDao.findUserRoles(user.getId()).contains(role);
        if (!alreadyHasRole)
            this.jdbcTemplate.update("INSERT INTO user_roles(id_user,id_role) VALUES (?,?)", user.getId(), role.getId());
    }

    public void removeRole(User user, Role role){
        boolean alreadyHasRole = this.roleDao.findUserRoles(user.getId()).contains(role);
        if (alreadyHasRole)
            this.jdbcTemplate.update("DELETE FROM user_roles WHERE id_user=? and id_role=?", user.getId(), role.getId());
    }
}

class UserRowMapper implements RowMapper<User> {

    final private RoleDao roleDao;

    public UserRowMapper(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRegistrationDate(rs.getDate("registration_date"));
        user.setLocked(rs.getBoolean("locked"));
        user.setRoles(roleDao.findUserRoles(user.getId()));
        return user;
    }
}