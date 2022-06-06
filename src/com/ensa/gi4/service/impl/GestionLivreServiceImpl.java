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
    public void showAll() {

    }

    @Override
    public void addNew(Materiel materiel) {

    }

    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("toto");

    }
}
