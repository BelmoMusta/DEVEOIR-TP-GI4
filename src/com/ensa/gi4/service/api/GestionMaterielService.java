package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

public interface GestionMaterielService {
    void init();
    void listerMateriels();
    void rechercheMateriel(Materiel materiel);
    void ajouterNouveauMateriel(Materiel materiel);
    void allouerMateriel(Materiel materiel, User user);
    void RendreMateriel(Materiel materiel,String username);
    void listerMaterielsAlloues();
    void listerMaterielsAllouesParUser(String username);
    void supprimerMateriel(Materiel materiel);
    void modifierMateriel(Materiel materiel,String nouveauCode);
    //  9- Afficher la liste des matériels alloués par chaque utilisateur
}

