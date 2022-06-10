package com.ensa.gi4.datatabase.impl;

import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.UtilisateurDAO;
import com.ensa.gi4.modele.Utilisateur;

@Repository
public class UtilisateurDaoImpl extends GenericDAO<Utilisateur> implements UtilisateurDAO{

	//Les requêtes SQL
	String queryLogin = "SELECT * FROM utilisateur WHERE username=? AND password=?;";
	
	//La méthode d'authentification
	public Utilisateur login(Utilisateur utilisateur) {
		
		return super.login(queryLogin, utilisateur);
	}
	 
	 @Override
	 protected UtilisateurRowMapper getRowMapper() {
		 
		 return new UtilisateurRowMapper();
	 }
	 
}
