package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Personne;

public interface PersonneDAO {
	Personne findPersonne(String nom, String pw);
	void ajouterPersonne(String nom, String pw);
	 String getRole(String name);
	 boolean allouerMateriel(String name,String duree);
	 boolean rendreMateriel(String name);
	 	
}
