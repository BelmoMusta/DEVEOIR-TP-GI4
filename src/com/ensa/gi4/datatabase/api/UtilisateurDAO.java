package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Utilisateur;

public interface UtilisateurDAO {
	
	//La signature de la méthode à implémenter
	public Utilisateur login(Utilisateur utilisateur);
}
