package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {
    void init();
    void listerMateriel();

    void listerAllocations();

    void chercherMaterielID(int id);

    void ajouterNouveauMateriel(Materiel materiel);

    void modifierMateriel(int id);

    void supprimerMateriel(int id);

    void allouerMateriel(int id, int userID, int quantity);

    void affihcerMaterielAllouerParUtilisateur(int id);

    void rendreMateriel(int id, int userID, int quantity);

    void changerDisponibilte(int id);
}
