package com.ensa.gi4;

import com.ensa.gi4.controller.GestionMaterielController;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Utilisateur;
import com.ensa.gi4.service.api.GestionUtilisateurService;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SuppressWarnings("all")
@ComponentScan
public class AppGestionMateriel {
    private static final ApplicationContext APPLICATION_CONTEXT;
   

    static { // bloc static pour initilialisation
      	
        APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(AppGestionMateriel.class);
    }

    public static void main(String[] args) {
        GestionMaterielController gestionMaterielController = (GestionMaterielController) APPLICATION_CONTEXT.getBean("controllerPricipal");
        Utilisateur connexion;
        do{
        	connexion= gestionMaterielController.connection();
        } while(connexion==null);
        while (true) {
             gestionMaterielController.afficherMenu(connexion.getUsername(), connexion.getPassword(), connexion.getRole());
         }
    }
}
