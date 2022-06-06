package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    MaterielDao materielDao;

    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void listerMateriel() {

        for (Materiel i : materielDao.findAll()){
            System.out.println( i.getName() + " code: "+i.getCode());
        }
    }

    @Override
    public void chercherMateriel() {
        System.out.println("entrer l'id'du materiel que vous cherchez");
        Scanner id = new Scanner(System.in);
        Long ida = id.nextLong();

        try
        {
            System.out.println(materielDao.findOne(ida));
        } catch (Exception e) {
            System.out.println("Le materiel avec l'id "+ ida +" n'existe pas");
        }
    }

    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {

        System.out.println("L'ajout du matériel " + materiel.getName() + " effectué avec succès !");
    }
}
