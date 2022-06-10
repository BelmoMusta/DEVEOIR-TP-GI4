package com.ensa.gi4.service.api;


import com.ensa.gi4.modele.Person;

public interface GestionMaterielService {

    public void listerMateriel();
    public void ajouterNouveauMateriel();
    public void chercherMateriel();
    public void supprimerMateriel();
    public void modifierMateriel();
    public void allouerMateriel(Person person);
    public void rendreMateriel();
    public void listerMaterielAllouerUserId();
    public void listerMaterielAllouerYourUserId(Person person);
    public void listerMaterielAllouer();
}