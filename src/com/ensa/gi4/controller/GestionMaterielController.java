package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.impl.GestionMaterielServiceImpl;
import com.ensa.gi4.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("controllerPrincipal")
public class GestionMaterielController {

    @Autowired
    UserServiceImpl userService ;

    @Autowired
    GestionMaterielServiceImpl gestionMaterielService;
    @Autowired
    ApplicationPublisher publisher;



    static Scanner scanner  = new Scanner(System.in);


    public void showLogin(){
        System.out.println("----------Connecter-----------");
        System.out.println("Utilisateur : ");
        String username = scanner.next() ;
        System.out.println("Mot de passe : ");
        String password = scanner.next() ;
        if(userService.login(username,password)){
            User userLogged = userService.getUserByName(username);
            userService.setUsercurrent(userLogged);
            this.showHome();
        }else{
            System.out.println("Erreur de connection");
            System.out.println("[1] : Réessayer");
            System.out.println("[2] : Sortir");
            System.out.print(">>> ");
            String entry = scanner.next();
            checkEntryAfterError(entry);
        }
    }

    public void showHome() {
        if(this.userService.isAdmin()){
            this.showAdminMenu();
        }else {
            this.showUserMenu();
        }
    }

    public void showAdminMenu(){
        String welcomeMsg = String.format("---Bienvenue %s ---",userService.getUsercurrent().getUsername());
        System.out.println(welcomeMsg);
        System.out.println("[1] : Chercher un matériel");
        System.out.println("[2] : Créer un nouveau matériel");
        System.out.println("[3] : Supprimer un matériel");
        System.out.println("[4] : Modifier les informations d'un matériel");
        System.out.println("[5] : Marquer un matériel indisponible");
        System.out.println("[6] : Allouer un matériel");
        System.out.println("[7] : Rendre un matériel");
        System.out.println("[8] : Afficher la liste des matériels alloués par lui même");
        System.out.println("[9] : Afficher la liste des matériels alloués par chaque utilisateur");
        System.out.println("[10] : Afficher la liste de tous les matériels");
        System.out.println("[0] : Sortir de l'application");
        System.out.print(">>> ");
        String entry = scanner.next();
        checkAdminEntry(entry);

    }
    public void showUserMenu(){
        String welcomeMsg = String.format("---Bienvenue %s ---",userService.getUsercurrent().getUsername());
        System.out.println(welcomeMsg);
        System.out.println("[1] : Chercher un matériel");
        System.out.println("[2] : Allouer un materiel");
        System.out.println("[3] : Rendre un matériel");
        System.out.println("[4] : Afficher la liste des matériels alloués par lui même");
        System.out.println("[5] : Afficher la liste de tous les matériels");
        System.out.println("[0] : Sortir de l'application");
        System.out.print(">>> ");
        String entry = scanner.next();
        checkUserEntry(entry);
    }

    public void checkAdminEntry(String entry){
        switch(entry) {
            case "0" : sortirDeLApplication();
            case "1" : sortirDeLApplication();
            case "2" : sortirDeLApplication();
            case "3" : sortirDeLApplication();
            case "4" : sortirDeLApplication();
            case "5" : sortirDeLApplication();
            case "6" : sortirDeLApplication();
            case "7" : sortirDeLApplication();
            case "8" : sortirDeLApplication();
            case "9" : sortirDeLApplication();
            case "10" : showAllItems();
            default :
                System.out.println("Choix invalide ! Veuiller réssayer");
                showAdminMenu();
        }
    }

    public void checkUserEntry(String entry){
        switch(entry){
            case "0" : sortirDeLApplication();
            case "1" : sortirDeLApplication();
            case "2" : sortirDeLApplication();
            case "3" : sortirDeLApplication();
            case "4" : sortirDeLApplication();
            case "5" : showAllItems();
            default :
                System.out.println("Choix invalide ! Veuiller réssayer");
                showUserMenu();
        }
    }

    public void checkEntryAfterError(String entry){
        switch(entry){
            case "1" : showLogin();
            case "2" : sortirDeLApplication();
            default: sortirDeLApplication();
        }
    }

    public void showAllItems(){
        this.gestionMaterielService.showAll();
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
