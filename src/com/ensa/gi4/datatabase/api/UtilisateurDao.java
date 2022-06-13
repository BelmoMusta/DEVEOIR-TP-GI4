package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Utilisateur;

public interface UtilisateurDao {
	Utilisateur findUtilisateur(String nom, String password);
}
