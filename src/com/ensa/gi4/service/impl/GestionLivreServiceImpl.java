package com.ensa.gi4.service.impl;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.SmartInitializingSingleton;

import java.util.List;

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
    public void ajouterNouveauMateriel(Materiel materiel) {

        System.out.println("L'ajout du matériel " + materiel.getName() + " effectué avec succès !");
    }

    @Override
    public void supprimerUnMaterial(int id) {

    }

    @Override
    public boolean checkAvantSupprimer(int id) {
        return false;
    }

    @Override
    public void modifierUnMateriel(int id, String code, String name) {

    }

    @Override
    public void chercherMateriel(int id) {

    }

    @Override
    public void materielIndisponible(int id) {

    }

    @Override
    public void materialAlloue(int id, String duree, int id_user, String username) {

    }

    @Override
    public void renderMateriel(int id) {

    }

    @Override
    public void AllMaterielAlloue(int user_id) {

    }

    @Override
    public void allAllloue() {

    }


    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("****Welcome To Spring ****");

    }
}
