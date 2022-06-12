package com.ensa.gi4.controller;

import  com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.*;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.SignInUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

//TODO: PUBLISHER LISTENER


@Component("controllerPrincipal")
public class GestionMaterielController {

    @Autowired
    ApplicationPublisher publisher;
    @Value("${menu.utilisateur}")
    String userMenu;
    @Value("${menu.admin}")
    String adminMenu;
    @Autowired
    @Qualifier("gestionMaterielService")
    GestionMaterielService gestionMaterielService;
    @Autowired
    User currentUser;
    @Value("${alert.invalidChoice}")
    String invalidChoice;
    @Value("${alert.noRenting}")
    String noRenting;
    @Value("${message.typeMateriel}")
    String makeChoice;

    @Autowired
    SignInUserService signInUserService;
    Scanner scanner  = new Scanner(System.in);


    public void afficherMenuUser() {
        System.out.println(userMenu);
        String entry = scanner.next();
        switch(entry) {
            //menu.utilisateur = 1- pour chercher un matériel\n2- pour allouer un matériel\n3- pour rendre un materiel\n4- Afficher la liste des matériels alloués\n5- Afficher la liste de tous les matériels\n0- pour sortir de l'application

            case "1":
                this.searchMaterial();
                afficherMenuUser();
                break;
            case "2":
                this.rentMaterial();
                afficherMenuUser();
                break;
            case "3":
                this.returnMaterial();
                afficherMenuUser();
                break;
            case "4":
                this.viewRentedMaterials();
                afficherMenuUser();
                break;
            case "5":
                this.viewAllMaterials();
                afficherMenuUser();
                break;
            case "0":
                this.sortirDeLApplication();
                break;
            default:
                System.out.println(invalidChoice);
                this.afficherMenuUser();

        }

    }

    public void afficherMenuAdmin(){
        System.out.println(adminMenu);
        String entry = scanner.next();


        switch (entry) {
            case "1":
                this.searchMaterial();
                this.afficherMenuAdmin();
                break;
            case "2":
                this.createNewMaterial();
                this.afficherMenuAdmin();
                break;
            case "3":
                this.deleteMaterial();
                this.afficherMenuAdmin();
                break;
            case "4":
                this.editMaterial();
                this.afficherMenuAdmin();
                break;
            case "5":
                this.markMaterialUnavailable();
                this.afficherMenuAdmin();
                break;
            case "6":
                this.rentMaterial();
                this.afficherMenuAdmin();
                break;
            case "7":
                this.returnMaterial();
                this.afficherMenuAdmin();
                break;
            case "8":
                this.viewRentedMaterials();
                this.afficherMenuAdmin();
                break;
            case "9":
                this.viewRentedMaterialsByAllUser();
                this.afficherMenuAdmin();
                break;
            case "10":
                this.viewAllMaterials();
                this.afficherMenuAdmin();
            case "0":
                this.sortirDeLApplication();
                break;
            default:
                this.afficherMenuAdmin();
        }

    }
    public void createNewMaterial(){
        Materiel materiel = new Materiel() {
        };
        System.out.println(makeChoice);
        String entry = scanner.next();
        switch (entry){
            case "1":
                materiel = new Livre();
                publisher.publish(new MyEvent<>(new Livre(), EventType.ADD));
                break;
            case "2":
                materiel = new Chaise();
                publisher.publish(new MyEvent<>(new Chaise(), EventType.ADD));
                break;
            default:
                System.out.println(invalidChoice);
                break;
        }

        System.out.println("Création d'un nouveau matériel en cours... .");
        System.out.println("Entrez le nom du matériel : \n");
        String name = scanner.next();
        System.out.println("Entrez le code du matériel : \n");
        String code = scanner.next();
        materiel.setCode(code);
        materiel.setName(name);
        materiel.setRented(false);
        gestionMaterielService.addNewMaterial(materiel);

    }
    public void deleteMaterial(){

        System.out.println("Entrez le nom du matériel à supprimer : \n");
        String name = scanner.next();
        Materiel materiel = gestionMaterielService.searchMaterialByName(name);
        System.out.println("Suppression en cours ....\n");
        gestionMaterielService.deleteMaterial(materiel);
        System.out.println("Suppression réussite ....\n");
    }
    public void editMaterial(){

        System.out.println("Entrez le nom du matériel à modifier : \n");
        String name = scanner.next();
        Materiel materiel = gestionMaterielService.searchMaterialByName(name);
        System.out.println("Entrez le nom du matériel final : \n");
        String finalName = scanner.next();
        gestionMaterielService.editMateriel(materiel,finalName);

    }
    public void markMaterialUnavailable(){

        System.out.println("Entrez le nom du matériel à modifier : \n");
        String name = scanner.next();
        Materiel materiel = gestionMaterielService.searchMaterialByName(name);
        gestionMaterielService.markUnavailable(materiel);

    }
    public void viewRentedMaterialsByAllUser(){

        List<RentedMaterial> rentedMaterials = gestionMaterielService.viewRentedOfAllUsers();
        for( RentedMaterial rentedMaterial : rentedMaterials ){
            Materiel materiel = gestionMaterielService.seachMaterialById(rentedMaterial.getIdMateriel());
            User user = gestionMaterielService.searchUserById(rentedMaterial.getIdUser());
            System.out.println("Nom Allocateur : \t" + user.getIdUser() + "\t Nom matériel :\t" + materiel.getName());
        }
    }

    public void searchMaterial(){
        System.out.println("Entrez le nom du matériel à chercher : \n");
        String name = scanner.next();
        Materiel materiel = gestionMaterielService.searchMaterialByName(name);
        String availability=(materiel.isRented()? "Non disponible pour le moment": "Disponible");
        System.out.println("Nom du materiel :\t" +materiel.getName()+"\nCode du matériel : \t"+materiel.getCode()+"\nEtat : \t"+availability);
    }

    public void rentMaterial(){
        User user = signInUserService.getCurrentUser();
        System.out.println("Entrez le nom du matériel à allouer : \n");
        String name = scanner.next();
        Materiel materiel = gestionMaterielService.searchMaterialByName(name);

        int count = gestionMaterielService.checkRentedBeforeRenting(user);
        if(count == 5){
            System.out.println(noRenting);
        }else{
            System.out.println("Allocation en cours ... .");
            gestionMaterielService.rentMateriel(materiel,user);
        }

    }

    public void returnMaterial(){

        System.out.println("Entrez le nom du matériel à rendre : \n");
        String name = scanner.next();
        Materiel materiel = gestionMaterielService.searchMaterialByName(name);
        System.out.println("Remise du matériel en cours ... .");
        gestionMaterielService.returnMateriel(materiel,currentUser);

    }

    public void viewRentedMaterials(){
        System.out.println("Récuperation du matériel alloué en cours... . : \n");
        List<RentedMaterial> rentedMaterials = gestionMaterielService.viewRentedMaterielsByEachUser(currentUser);
        for( RentedMaterial rentedMaterial: rentedMaterials){
            Materiel materiel = gestionMaterielService.seachMaterialById(rentedMaterial.getIdMateriel());
            System.out.println("Nom matériel : \t "+ materiel.getName() +"\tCode Matériel : \t" + materiel.getCode()+'\n');
        }
    }
    public void viewAllMaterials(){

        System.out.println("Récuperation de la liste de matériels en cours... . : \n");
        List<Materiel> materiels = gestionMaterielService.listMaterial();
        for(Materiel materiel : materiels){
            String availability=(materiel.isRented()? "Non disponible pour le moment": "Disponible");
            System.out.println("Nom Matériel : \t" + materiel.getName() + "\t Code : \t" +materiel.getCode() + "\t Etat : \t" + availability +'\n' );
        }
    }
    public void sortirDeLApplication() {
        System.exit(0);
    }


}
