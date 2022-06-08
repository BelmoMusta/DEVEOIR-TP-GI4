package com.ensa.gi4.datatabase.api;

import java.util.List;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Personne;

public interface PersonneDAO {
	Personne findPersonne(String nom, String pw);

	Personne getPersonneConnecte();

	String determinerRole();
	boolean creerCompte(String name, String pw, String role);

}
