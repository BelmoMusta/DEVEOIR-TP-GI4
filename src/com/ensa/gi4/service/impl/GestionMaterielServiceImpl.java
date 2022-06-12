package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.AllocationsDao;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Allocations;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("materialService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    MaterielDao materielDao;
    @Autowired
    AllocationsDao allocationsDao;

    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void listerMateriel() {

        System.out.println(materielDao.findAll());
    }

    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name : ");
        String nom = scanner.nextLine();
        System.out.print("Code : ");
        String code = scanner.nextLine();
        System.out.print("Availability : ");
        int availability = scanner.nextInt();
        materiel.setName(nom);
        materiel.setCode(code);
        materiel.setAvailability(availability);
        materiel.set_available(true);
        materielDao.addMateriel(materiel);
        System.out.println("L'ajout du matériel " + materiel.getName() + " effectué avec succès !");
      /*  ApplicationPublisher applicationPublisher = new ApplicationPublisher();
        applicationPublisher.publish(new MyEvent<>(materiel, EventType.ADD));*/
    }

    @Override
    public void supprimerMateriel(int id) {
        Materiel materiel = materielDao.findOne(id);
       /// if(materiel != null)
        materielDao.deleteMateriel(id);
        System.out.println("La suppression du materiel"+ materiel.getName()+ " effectué avec succès !");
       /* ApplicationPublisher applicationPublisher = new ApplicationPublisher();
        applicationPublisher.publish(new MyEvent<>(materiel, EventType.REMOVE));*/
    }

    @Override
    public void chercherrMateriel(int id) {
        Materiel materiel = materielDao.findOne(id);
        if(materiel != null) {
            System.out.println("\n");
            System.out.println(materiel);
        }
        else System.out.println("Le matériel que vous cherchez n'existe pas");
    }

    @Override
    public void modifierMateriel(int id) {
        Materiel materiel = materielDao.findOne(id);
        if(materiel != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Name : ");
            String nom = scanner.nextLine();
            System.out.print("Code : ");
            String code = scanner.nextLine();
            System.out.print("Availability : ");
            int availability = scanner.nextInt();
            materiel.setName(nom);
            materiel.setCode(code);
            materiel.setAvailability(availability);
            materielDao.editMateriel(id, materiel);
            System.out.println("La modification du materiel"+ materiel.getName()+ " effectué avec succès !");
          /*  ApplicationPublisher applicationPublisher = new ApplicationPublisher();
            applicationPublisher.publish(new MyEvent<>(materiel, EventType.UPDATE));*/
        }
        else System.out.println("\nCe matériel n'existe pas");
    }


    @Override
    public void allouerMateriel(int id, User user, int quantity) {
        Materiel materiel = materielDao.findOne(id);
        if (materiel.is_available()) {
            if (materiel.getAvailability() > 0 && quantity <= materiel.getAvailability()){

                    Allocations allocation = allocationsDao.findMaterielAllocatedByUserIDAndMaterielID(user.getId(), materiel.getId());
                    if (allocation == null)
                        materielDao.allocateMateriel(materiel, user, quantity, 0);
                    else
                        materielDao.allocateMateriel(materiel, user, quantity, allocation.getAvailability());
                    System.out.println("Allocation de " + quantity + "pieces de " + materiel.getName() + " avec succès !");
            }
            else {
                System.out.println("La quantité désirable n'existe pas ds le stock !");
            }
        }
    }

    @Override
    public void returnMateriel(int id, int userId) {
        Materiel materiel = materielDao.findOne(id);
        Allocations allocation = allocationsDao.findMaterielAllocatedByMaterielID(id);
        materielDao.returnMateriel(materiel, allocation,allocation.getAvailability());
    }

    @Override
    public void listerAllAllocations() {
        System.out.println(allocationsDao.findAll());
    }

    @Override
    public void listerAllocationsUser(int id) {
        System.out.println(allocationsDao.findMaterielAllocatedByUserID(id));
    }

    @Override
    public void editAvailability(Materiel materiel) {
        materielDao.editAvailability(materiel.getId(), materiel);
        System.out.println("\nLa disponibilité est modifiée avec succès !");

    }
}
