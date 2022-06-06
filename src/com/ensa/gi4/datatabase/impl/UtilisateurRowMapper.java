package com.ensa.gi4.datatabase.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ensa.gi4.datatabase.api.UtilisateurDao;
import com.ensa.gi4.modele.Utilisateur;

public class UtilisateurRowMapper implements RowMapper<Utilisateur> {

	@Override
	public Utilisateur mapRow(ResultSet resultSet, int arg1) throws SQLException {
		Utilisateur utilisateur = new Utilisateur() { // because it is abstract
	        };

	        String nom = resultSet.getString("username");
	        String password = resultSet.getString("password");
	        String role = resultSet.getString("role");
	        utilisateur.setUsername(nom);
	        utilisateur.setPassword(password);
	        utilisateur.setRole(role);

	        return utilisateur;
	}

}