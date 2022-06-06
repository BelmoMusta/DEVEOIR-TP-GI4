package com.ensa.gi4.service.impl;

import com.ensa.gi4.enums.Role;
import com.ensa.gi4.enums.UserCreateStatus;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.AuthenticationService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

@Service
public class AuthenticationServiceImpl implements AuthenticationService, InitializingBean {

    RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setUser_id(rs.getInt("user_id"));
        user.setHashed_password(rs.getString("hashed_password"));
        user.setUsername(rs.getString("username"));
        user.setRole_id(rs.getInt("role_id"));
        return user;
    };

    Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,15*1024,2);
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() { // from InitializingBean
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public String getPasswordHash(String password) {
        return encoder.encode(password);
    }

    @Override
    public User getUser(String password, String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql,rowMapper,username.toLowerCase());

        if(users.isEmpty())
            return null;
        else if(users.size() == 1 && isPasswordValid(users.get(0).getHashed_password(),password))
            return users.get(0);

        return null;
    }

    @Override
    public boolean isPasswordValid(String hash, String password) {
        return encoder.matches(password, hash);
    }

    @Override
    public UserCreateStatus createUser(String username, String password, Role role) {
        //System.out.println(username + " " + password + " " + role + " " + getPasswordHash(password));
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE username = ?",rowMapper,username.toLowerCase());
        if(users.size() != 0)
            return UserCreateStatus.USER_EXISTS;

        if(jdbcTemplate.update("INSERT INTO users (username,hashed_password,role_id) VALUES (?,?,?)",username.toLowerCase(),getPasswordHash(password),role.ordinal()) == 1)
            return UserCreateStatus.CREATED_SUCCESSFULLY;

        return UserCreateStatus.DATABASE_ERROR;
    }
}
