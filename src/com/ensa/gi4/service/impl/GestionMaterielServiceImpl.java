package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    MaterielDao materielDao;

    @Autowired
    ApplicationPublisher publisher;


    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void listerMateriel() {
        System.out.println(materielDao.findAll());
    }

    @Override
    public void ajouterNouveauMateriel(String materielName,String materielType,String materielCode) {

        if (materielType.equals("livre") || materielType.equals("chaise")) {
            Materiel materiel = new Materiel() {

            };
            materiel.setName(materielName);
            materiel.setType(materielType);
            materiel.setAvailable(true);
            materiel.setCode(materielCode);
            materielDao.addMaterial(materiel);
            publisher.publish(new MyEvent<>(materiel, EventType.ADD));
            System.out.println("L'ajout du matériel " + materiel.getName() + " effectué avec succès !");
        } else {
            System.out.println("Le type du materiel entré n'existe pas pour le moment");
        }


    }

    @Override
    public void supprimerMateriel(String materielName, String materielCode) {

        Materiel materiel = new Materiel() {
        };
        materiel.setName(materielName);
        materiel.setCode(materielCode);
        int success = materielDao.deleteMaterial(materiel);
        if (success != 0) {
            System.out.println("Le matériel " + materiel.getName() + " a été supprimé  avec succès !");
        } else {
            System.out.println("Ce materiel n'existe pas!!");
        }


    }

    @Override
    public Materiel chercherMateriel(String code) {


        Materiel mat = null;
        try {
            mat = materielDao.findOne(code);
            System.out.println(materielDao.findOne(code));
        } catch (Exception e) {
           // System.out.println(materielDao.findOne(code));
            System.out.println("Ce materiel n'existe pas");
        }

        return mat;

    }

    @Override
    public void modifierMateriel(String materielCode,String materielName,String materielType,String materielAvailable) {


        Materiel materiel = new Materiel() {
        };
        materiel.setCode(materielCode);
        materiel.setName(materielName);
        materiel.setType(materielType);
       if(materielAvailable.equals("oui")){
            materiel.setAvailable(true);
        } else {
            materiel.setAvailable(false);
        }
        //materiel.setAvailable(true);
        int success = materielDao.updateMateral(materiel);
        if (success != 0) {
            publisher.publish(new MyEvent<>(materiel, EventType.UPDATE));
            System.out.println("Le matériel " + materiel.getName() + " a été modifié  avec succès !");
        } else {
            System.out.println("Une erreur est parvenu. merci de reessayer !!");
        }
    }

    @Override
    public void modifierAvailable(String materielCode,String materielEtat) {

        Materiel materiel = new Materiel() {
        };
        materiel.setCode(materielCode);
        if(materielEtat.equals("oui")){
            materiel.setAvailable(true);
        } else {
            materiel.setAvailable(false);
        }

        int success = materielDao.updateAvailability(materiel);
        if (success != 0) {
            System.out.println("La modifaction de l'etat a été modifié  avec succès !");
        } else {
            System.out.println("Ce materiel n'existe pas!!");
        }
    }

}