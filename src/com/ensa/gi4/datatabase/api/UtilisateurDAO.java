package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Utilisateur;

public interface UtilisateurDAO {
	
	//La signature de la m�thode � impl�menter
	public Utilisateur login(Utilisateur utilisateur);
}
