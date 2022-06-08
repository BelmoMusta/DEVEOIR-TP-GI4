package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Personne;

public interface GestionPersonneService {
	Personne connecter(String nom, String pw);
	void creerCompte(String nom, String pw);
	 Boolean isAdmin(String name, String password);
	 void allouerMateriel(String nom, String duree);	
	 void rendreMateriel(String nom);

}
