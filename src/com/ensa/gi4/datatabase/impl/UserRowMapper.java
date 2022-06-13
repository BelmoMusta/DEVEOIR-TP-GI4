package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Utilisateur;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<Utilisateur> {
    @Override
    public Utilisateur mapRow(ResultSet resultSet, int i) throws SQLException {
        Utilisateur user = new Utilisateur();
        user.setUserId(resultSet.getInt("userid"));
        user.setUserName(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(resultSet.getString("role"));
        return user;
    }
}

