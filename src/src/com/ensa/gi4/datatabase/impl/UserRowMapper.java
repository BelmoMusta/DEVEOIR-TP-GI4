package com.ensa.gi4.datatabase.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.ensa.gi4.modele.Utilisateur;

public class UserRowMapper implements RowMapper<Utilisateur> {
    @Override
    public Utilisateur mapRow(ResultSet resultSet, int i) throws SQLException {
    	Utilisateur utilisateur = new Utilisateur() { // because it is abstract
        };

       
        Long id_utilisateur = resultSet.getLong("id_utilisateur");
        String username = resultSet.getString("username");
        String role = resultSet.getString("role");
        String password= resultSet.getString("Password");
       
        utilisateur.setId_utilisateur(id_utilisateur);
        utilisateur.setUsername(username);
        utilisateur.setRole(role);
        utilisateur.setPassword(password);
  
        return utilisateur;
    }
}
