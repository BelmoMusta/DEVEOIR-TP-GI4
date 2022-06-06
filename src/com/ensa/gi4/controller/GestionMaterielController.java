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
       System.out.println(gestionUser.isAdmin(name, password));
       if(gestionUser.connexion(name, password)!=null){
    	   
       
       	 if(gestionUser.isAdmin(name, password)){
                //Scenario Pour Admin
       		 
       	 }
       	 else {
       		 
       		 //Scenario pour les employes 
       		 
       		 while(true) {
       			 System.out.println("Pour liseter les materiels, saisir 1: ");
       			System.out.println("Pour chercher un materiel,saisir 2: ");
       			System.out.println("Pour allouer un materiel saisir 3: ");
       			System.out.println("Pour rendre un materiel saisir 4: ");
       			String num = scanner.next();
       			
       			if(num.equals("1")) {
        			gestionMateriel.listerMateriel();
        		}
       			
        		else if(num.equals("2")) {
            		System.out.println("entrer son id : ");
            		String id = scanner.next();
        			gestionMateriel.findOneMateriel(Long.parseLong(id));
        		}
       			
        		else if(num.equals("3")) {
        			System.out.println("entrer le code du materiel ");
        			String code = scanner.next();
        			System.out.println("entrer la durée d'allocation ");
        			String duree = scanner.next();
        			gestionUser.allouerMateriel(code, duree);
        		}
        		
       			
       		 }
       	 }
       }
       else {
    	   System.out.println("echoue");
       }
        //publisher.publish(new MyEvent<>(new Livre(), EventType.ADD));
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
