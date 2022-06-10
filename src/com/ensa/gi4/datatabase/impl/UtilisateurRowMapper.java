package com.ensa.gi4.datatabase.impl;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.ensa.gi4.modele.Utilisateur;

public class UtilisateurRowMapper implements RowMapper<Utilisateur> {

	@Override
	public Utilisateur mapRow(ResultSet resultSet, int i) throws SQLException {
		
		Utilisateur utilisateur = new Utilisateur();
		
		Long idUser = resultSet.getLong("idUser");
		String username = resultSet.getString("username");
		String password = resultSet.getString("password");
		String role = resultSet.getString("role");
		
		utilisateur.setIdUser(idUser);
		utilisateur.setUsername(username);
		utilisateur.setPassword(password);
		utilisateur.setRole(role);
		
		
		return utilisateur;
	}

	
	
}
