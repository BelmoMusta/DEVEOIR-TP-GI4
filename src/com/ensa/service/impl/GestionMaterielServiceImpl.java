package com.ensa.service.impl;

import com.ensa.modele.Allocation;
import com.ensa.modele.Materiel;
import com.ensa.service.api.GestionMaterielService;
import org.springframework.stereotype.Component;

@Component("materiel")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    // bd goes here
    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void listerMateriel() {
        System.out.println("Liste de matériel :\n 3 Livres \n 4 chaises");
    }

    @Override
    public void allouerMateriel(Allocation code) {

    }

    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {
        // à compléter
        System.out.println("L'ajout du matériel " + materiel.getName() + " effectué avec succès !");
    }

    @Override
    public void supprimerMateriel(String s) {

    }

    @Override
    public void modifierMateriel(Materiel materiel,String c) {

    }
}
