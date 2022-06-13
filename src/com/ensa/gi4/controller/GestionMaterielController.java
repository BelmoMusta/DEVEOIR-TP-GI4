package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.service.api.GestionMaterielService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {   
	@Autowired
    @Qualifier("materielService")
    private GestionMaterielService gestionMaterielService;
		
	 public GestionMaterielController() {
    }
	 //A revoir

    public GestionMaterielController(GestionMaterielService livreService) {
        this.gestionMaterielService = livreService;
    }
    
    
   
    /*
    private GestionMaterielService gestionLivreService;

    @Autowired
    @Qualifier("materielService")
    private GestionMaterielService gestionMaterielService;

    public void listerMateriel() {
        gestionMaterielService.listerMateriel();
    }

    public GestionMaterielController() {
    }

    public GestionMaterielController(GestionMaterielService livreService) {
        this.gestionLivreService = livreService;
    }

    public void afficherMenu() {
        System.out.println("1- pour lister le matériel, entrer 1");
        System.out.println("2- pour ajouter un nouveau matériel, entrer 2");
        System.out.println("0- pour sortir de l'application, entrer 0");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            sortirDeLApplication();
        } else if ("1".equals(next)) {
            listerMateriel();
        } else if ("2".equals(next)) {
            // ajouter nouveau matériel
        } else {
            System.out.println("choix invalide");
        }
    }*/

    /*
    @Autowired
    ApplicationPublisher publisher;

    public void afficherMenu() {
        Scanner scanner  = new Scanner(System.in);
        publisher.publish(new MyEvent<>(new Livre(), EventType.ADD));
    }*/
   
   

}
