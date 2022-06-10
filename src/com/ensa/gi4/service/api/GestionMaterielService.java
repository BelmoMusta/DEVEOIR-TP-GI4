package com.ensa.gi4.service.api;


public interface GestionMaterielService {
    void init();
    void listerMateriel(); // recuperer la liste de tous les matériels
    void ajouterNouveauMateriel(); // pour ajouter un nouveau materiel 
    void chercherMateriel(); // pour chercher un materiel
    void supprimerMateriel(); // pour supprimer un materiel 
    void modifierDisponibilitéMateriel();
    void allouerMateriel(int idU);
    void rendreMateriel(int idU);
    void modifierMateriel();
    void listerVosMaterielAllouer(int idU);
    void listerEmployeMaterielAllouer();
}
