package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {
    void init();
    void listerMateriel();
    void findMateriel(Long id);
    void ajouterNouveauMateriel(Materiel materiel);
    void supprimerMateriel(Materiel M);
    void modifierInfosMateriel(Materiel M);
    void indisponibleMateriel(Long id);
	public void afficherMaterielAllouerParUtilisateur();
	boolean listerMaterielAlloue();
}
