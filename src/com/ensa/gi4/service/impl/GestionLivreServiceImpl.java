package com.ensa.gi4.service.impl;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class GestionLivreServiceImpl {


    public void listerMateriel() {
        System.out.println("Liste de matériel :\n 3 Livres \n 4 chaises");
    }


    public void ajouterNouveauMateriel(Materiel materiel) {

        System.out.println("L'ajout du matériel " + materiel.getName() + " effectué avec succès !");
    }


}
