package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Utilisateur;

public interface GestionUtilisateurService {
	Utilisateur login();
	void afficherMenuAdmin();
	void sortirDeLApplication();
	void afficherMenuEmployee();
	void gestionChoixEmployee(Utilisateur user, String next);
	void gestionChoixAdmin(Utilisateur user, String next);
}
