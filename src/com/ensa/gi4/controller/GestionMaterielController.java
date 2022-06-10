package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component("controllerPricipal")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GestionMaterielController {
    private final Scanner scanner = new Scanner(System.in);
    private final String[] TYPES = new String[]{"livre", "chaise"};
    private final Map<String, Class<? extends Materiel> > materiels = new HashMap<>(){{
        put("livre", Livre.class);
        put("chaise", Chaise.class);
    }};

    final ApplicationPublisher publisher;
    final GestionMaterielService gestionMaterielService;

    public void afficherMenu() {
        System.out.println("Bienvenue dans le gestionnaire de materiel !");
        System.out.println("1 - Lister meteriels");
        System.out.println("2 - Ajouter un materiel");
        System.out.println("3 - Modifier un materiel");
        System.out.println("4 - Supprimer un materiel");
        System.out.println("5 - Allouer un materiel");
        System.out.println("6 - Desallouer un materiel");
        System.out.println("0 - Sortir de l'application");
        System.out.print("Entrer votre choix : ");

        switch (scanner.next()){
            case "1" -> listerMateriel();
            case "2" -> ajouterMateriel();
            case "3" -> modifierMateriel();
            case "4" -> supprimerMateriel();
            case "5" -> allouerMatriel();
            case "6" -> desallouerMateriel();
            case "0" -> sortirDeLApplication();
            default -> System.out.println("Entrée est invalide !");
        }
    }

    private void listerMateriel() {
        gestionMaterielService.listerMateriel();
    }

    private void ajouterMateriel() {
        String type = null;
        do{
            System.out.print("Enter le type du materiel : ");
            type = scanner.next().toLowerCase();
        }while (!Arrays.asList(TYPES).contains(type));
        Materiel materiel = null;
        try {
            materiel = materiels.get(type).getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException  e) {
            e.printStackTrace();
        }
        scanner.nextLine();

        System.out.print("Entrer le nom : ");
        materiel.setName(scanner.nextLine());
        System.out.print("Entrer le stock : ");
        materiel.setStock(scanner.nextInt());

        if(materiel instanceof Livre){
            Livre livre = (Livre) materiel;
            System.out.print("Entrer le nom d'auteur : ");
             livre.setAuthor(scanner.next());
             gestionMaterielService.ajouterLivre(livre);
        }else{
            Chaise chaise = (Chaise) materiel;
            System.out.print("Entrer la matière première : ");
            chaise.setMadeof(scanner.next());
            gestionMaterielService.ajouterChaise(chaise);
        }

    }

    private void modifierMateriel() {
        System.out.print("Enter l'id de materiel que vous voulez modifier : ");
        this.gestionMaterielService.modifierMateriel(scanner.nextInt());
    }

    private void supprimerMateriel() {
        System.out.print("Enter l'id de materiel que vous voulez supprimer : ");
        this.gestionMaterielService.supprimerMateriel(scanner.nextInt());
    }

    private void allouerMatriel() {
        System.out.print("Enter l'id de materiel que vous voulez allouer : ");
        this.gestionMaterielService.allouerMateriel(scanner.nextInt());
    }

    private void desallouerMateriel() {
        System.out.print("Enter l'id de materiel que vous voulez rendre: ");
        this.gestionMaterielService.desallouerMateriel(scanner.nextInt());
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
