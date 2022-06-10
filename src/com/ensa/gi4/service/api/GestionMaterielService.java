package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.User;

public interface GestionMaterielService {
     void listerMateriel();
     void ajouterNouveauMateriel();
     void chercherMateriel();
     void supprimerMateriel();
     void modifierMateriel();
     void allouerMateriel(User user);
     void rendreMateriel();
     void listerMaterielAllouerUserId();
     void listerMaterielAllouerYourUserId(User user);
     void listerMaterielAllouer();
}
