package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {
	void init();

	void listerMateriel();

	void ajouterNouveauMateriel(Materiel materiel);

	void findMateriel(Long id);

	void supprimerMateriel(int id);

	void modifierMateriel(int id, String nom, String code);

	void marquerMaterielIndisponible(int id);

	void afficherMaterielAllouerParUtilisateur();
	
	void allouerMateriel(String nom, String duree);

	void rendreMateriel(int id);

	boolean listerMaterielAlloue();

	

}
