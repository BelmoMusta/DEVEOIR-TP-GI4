package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

public interface GestionMaterielService {
    void init();
    //lister tous le materiel
    void listerMateriel();
    //chercher un materiel
    void rechercheMateriel(Materiel materiel);
    //ajouter un materiel
    void ajouterNouveauMateriel(Materiel materiel);
    //allouer un materiel
    void allouerMateriel(Materiel materiel,User user);
    //rendre un materiel
    void RendreMateriel(Materiel materiel,String username);
    //lister tous les materiels alloués
    void listerMaterielAlloue();
    //lister le materiel alloués par l'utilisateur lui meme
    void listerMaterielAlloueByName(String username);
    //supprimer un materiel 
    void supprimerMateriel(Materiel materiel);
    //modifier un materiel
    void modifierMateriel(Materiel materiel,String nouveauCode);
}
