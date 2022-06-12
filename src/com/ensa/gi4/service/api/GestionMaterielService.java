package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {
	void init();

	void listerMateriel();

	void ajouterNouveauMateriel(Materiel materiel);

	void findMateriel(Long id);

	String supprimerMateriel(Long id);

	String modifierMateriel(Long id,  String code);

	void marquerMaterielIndisponible(Long id);

	void afficherMaterielAllouerParUtilisateur();
	
	void allouerMateriel(String nom, String duree);

	void rendreMateriel(Long id);

	boolean listerMaterielAlloue();

	

}
