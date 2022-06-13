package com.ensa.gi4.service.api;

public interface GestionMaterielService {
	void listerMateriel();
	void modifierMateriel();
	void supprimerMateriel();
	void allouerMateriel(int userId);
	void chercherMateriel();
    void ajouterNouveauMateriel();
	void rendreMateriel(int userId);
	void afficherMaterielsAllouesParUtilisateur();
	void afficherMaterielsAllouesParMoi(int i);
	void marquerIndisponible();   
}
