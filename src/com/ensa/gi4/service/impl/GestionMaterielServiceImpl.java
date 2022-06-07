package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.DAO;
import com.ensa.gi4.modele.Material;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    DAO<Material> materialDao;

    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void listerMateriel() {
        System.out.println(materialDao.getAll());
    }

    @Override
    public void ajouterNouveauMateriel(Material materiel) {

        System.out.println("L'ajout du matériel " + materiel.getName() + " effectué avec succès !");
    }
}
