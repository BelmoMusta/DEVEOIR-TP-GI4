package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {

    void listerMateriel();

    void listerAllocations();

    void listerAllocationsParUser(int id);

    void ajouterNouveauMateriel(Materiel materiel);

    void supprimerMateriel(String nom);

    void modifierMateriel(String nom);

    void chercherMateriel(String nom);

    void allouerMateriel(String nom, int userID);

    void rendreMateriel(String nom, int userID);

    void changerDisponibilte(String nom);

}
