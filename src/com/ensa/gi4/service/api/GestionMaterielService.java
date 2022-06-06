package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {
    void init();
    void listerMateriel();
    void chercherMateriel(Long code);
    void ajouterNouveauMateriel(Materiel materiel);
    void deleteMateriel(Long id);
 /*   void modifierMateriel(String code);
 
    void supprimerMateriel(String code);
    void marquerMaterielIndisponible(String code);
    void allouerMateriel(String code);
    void rendreMateriel(String code);
    void listeMaterielUtilisateur(String code);*/
}
