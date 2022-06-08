package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {
    void init();
    void listerMateriel();
    void ajouterNouveauMateriel(Materiel materiel);
	void findOneMateriel(long id);
	 void supprimerMateriel(int id);
	  void modifierMateriel(int id, String nom, String code);
}
