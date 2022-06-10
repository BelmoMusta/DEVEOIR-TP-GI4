package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielAllocatedDao;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.MaterielAllocated;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    MaterielDao materielDao;

    @Autowired
    MaterielAllocatedDao materielAllocatedDao;

    @Autowired
    ApplicationPublisher applicationPublisher;

    @Override
    public void modifierMateriel(int id) {
        Materiel materiel = materielDao.findOne(id);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Code : ");
        String code = scanner.nextLine();
        System.out.print("Quantité : ");
        int quantity = scanner.nextInt();
        materiel.setName(nom);
        materiel.setCode(code);
        materiel.setQuantity(quantity);
        materielDao.editMateriel(id,materiel);
        applicationPublisher.publish(new MyEvent<>(materiel, EventType.UPDATE));
    }

    @Override
    public void supprimerMateriel(int id) {
        materielDao.deleteMateriel(id);
        System.out.println("Materiel supprimé avec succés");
    }

    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void listerMateriel() {
        System.out.println(materielDao.findAll());
    }

    @Override
    public void listerAllocations() {
        System.out.println(materielAllocatedDao.findAll());
    }

    @Override
    public void chercherMaterielID(int id) {
        System.out.println(materielDao.findOne(id));
    }

    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Code : ");
        String code = scanner.nextLine();
        System.out.print("Quantité : ");
        int quantity = scanner.nextInt();
        materiel.setName(nom);
        materiel.setCode(code);
        materiel.setAvailability(true);
        materiel.setQuantity(quantity);
        materielDao.addMateriel(materiel);
        applicationPublisher.publish(new MyEvent<>(materiel, EventType.ADD));
    };

    @Override
    public void allouerMateriel(int id, int userID, int quantity) {
        Materiel materiel = materielDao.findOne(id);
        if (materiel.isAvailable()) {
            if (materiel.getQuantity() > 0){
                if (quantity <= materiel.getQuantity()){
                    MaterielAllocated materielAllocated = materielAllocatedDao.findMaterielAllocatedByUserIDAndMaterielID(userID, materiel.getId());
                    if (materielAllocated == null)
                        materielDao.allocateMateriel(materiel, userID, quantity, false, 0);
                    else
                        materielDao.allocateMateriel(materiel, userID, quantity, true, materielAllocated.getQuantity());
                    System.out.println("Vous avez alloué " + quantity + " " + materiel.getName() + "(s) avec succès");
                }
                else
                    System.out.println("La quantité disponible est : " + materiel.getQuantity());
            } else {
                System.out.println("Materiel en rupture de stock");
            }
        } else {
            System.out.println("Materiel non disponible");
        }
    }

    @Override
    public void affihcerMaterielAllouerParUtilisateur(int id)  {
        System.out.println(materielAllocatedDao.findMaterielAllocatedByUserID(id));
    }

    @Override
    public void rendreMateriel(int id, int userID, int quantity) {
        Materiel materiel = materielDao.findOne(id);
        MaterielAllocated materielAllocated = materielAllocatedDao.findMaterielAllocatedByUserIDAndMaterielID(userID, materiel.getId());

        if (materielAllocated != null) {
            if (quantity <= materielAllocated.getQuantity()) {
                materielDao.returnMateriel(materiel, materielAllocated, quantity);
            }
            else
                System.out.println("La quantité alloué est : " + materielAllocated.getQuantity());
        }
        else {
            System.out.println("Ce materiel n'est pas alloué");
        }
    }

    @Override
    public void changerDisponibilte(int id) {
        Materiel materiel = materielDao.findOne(id);
        materielDao.editMaterielAvailability(id,materiel);
    }
}
