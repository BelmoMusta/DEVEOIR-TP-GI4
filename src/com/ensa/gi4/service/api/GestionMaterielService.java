package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

public interface GestionMaterielService {
    public void init();
    public void listerMateriel();
    public void ajouterNouveauMateriel();
    public void chercherMateriel();
    public void supprimerMateriel();
    public void modifierMateriel();
    public void allouerMateriel(User user);
    public void rendreMateriel();
    public void listerMaterielAllouerUserId();
    public void listerMaterielAllouerYourUserId(User user);
    public void listerMaterielAllouer();
}
