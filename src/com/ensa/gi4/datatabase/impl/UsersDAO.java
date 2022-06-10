package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.DAO;
import com.ensa.gi4.modele.Material;
import com.ensa.gi4.modele.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("usersDao")
public class UsersDAO implements DAO<User>, InitializingBean {

    @Autowired
    UserRowMapper rowMapper;

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users JOIN roles ON roles.role_id = users.role_id";

        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public Optional<User> getById(int id) {
        String sql = "SELECT * FROM USERS JOIN roles ON roles.role_id = users.role_id WHERE users.user_id = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, rowMapper,id);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        if (jdbcTemplate.update(sql, id) == 1)
            System.out.println("User deleted");
    }

    @Override
    public void update(int id, User user) {
        String sql = "UPDATE users SET username = ?, role_id = ?, hashed_password = ? WHERE user_id = ?";
        if (jdbcTemplate.update(sql, user.getUsername(), user.getRole_id(), user.getHashed_password(), id) == 1)
            System.out.println("User updated : " + user.getUsername());
    }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO users(username, role_id, hashed_password) VALUES (?, ?, ?)";
        if (jdbcTemplate.update(sql, user.getUsername(), user.getRole_id(), user.getHashed_password()) == 1)
            System.out.println("User created : " + user.getUsername());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
