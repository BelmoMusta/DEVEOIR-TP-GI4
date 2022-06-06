package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionPersonneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired

    ApplicationPublisher publisher;
    @Autowired 
    GestionPersonneService gestionPersonneService;
    @Autowired
    @Qualifier("materielService")
    GestionMaterielService gestionMaterielService;

    public void afficherMenu() {
    	System.out.println("----------------bonjour----------------");
    	System.out.println(" saisir votre nom ");
        Scanner scanner  = new Scanner(System.in);
        String name = scanner.next();
        System.out.println(" saisir votre mot de passe ");
        String pw = scanner.next();
      if(  gestionPersonneService.connecter(name, pw)!= null) {
    	  
    	 System.out.println("hi");
    	 gestionMaterielService.listerMateriel();
    	 pw = scanner.next();
      }else {
    	  System.out.println(" saisir 0 pour sortir de l'application");
    	  System.out.println(" saisir 1 pour réesayer à nouveau");
    	  String choix = scanner.next();
    	  if(choix.equals("0")) {
    		  sortirDeLApplication();
    	  }else {
    		  name=null;
    		  pw=null;
    		 afficherMenu();
    	  }
      }
        
       // publisher.publish(new MyEvent<>(new Livre(), EventType.ADD));
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
