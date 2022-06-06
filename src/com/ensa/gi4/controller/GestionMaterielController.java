package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired

    ApplicationPublisher publisher;
    
    @Autowired
    @Qualifier("materielService")
    GestionMaterielService gestionMateriel;
    
    @Autowired
    @Qualifier("gestionUser")
    GestionUserService gestionUser;
    
    

    public void afficherMenu() {
    System.out.println("************** Bienvenue **************");
       System.out.println("Veuillez entrer votre nom");
       Scanner scanner  = new Scanner(System.in); 
       String name = scanner.next();
       System.out.println("Veuillez entrer votre mot de passe");
       String password = scanner.next();
       
       if(gestionUser.connexion(name, password)!=null){
    	   
    	  System.out.println("Connexion reussie !"); 		
       
       	 
    	   
       }
       else {
    	   System.out.println("echoue");
       }
        publisher.publish(new MyEvent<>(new Livre(), EventType.ADD));
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
