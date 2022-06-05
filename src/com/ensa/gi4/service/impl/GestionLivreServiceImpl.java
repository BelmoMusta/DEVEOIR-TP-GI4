package com.ensa.gi4.service.impl;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.SmartInitializingSingleton;

public class GestionLivreServiceImpl implements GestionMaterielService, SmartInitializingSingleton {
    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void listerMateriel() {
        System.out.println("Liste de matériel :\n 3 Livres \n 4 chaises");
    }

    @Override
    public void chercherMateriel(long id) {

    }

    @Override
    public void ajouterMateriel(Materiel materiel) {

    }

    @Override
    public void supprimerMateriel(int id) {

    }


    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("toto");

    }
}
