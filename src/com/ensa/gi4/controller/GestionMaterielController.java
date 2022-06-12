package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
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
		  Materiel M= new Materiel() { // because it is abstract
	        };
		 
    		Scanner scanner  = new Scanner(System.in);

    	    		if(gestionPersonneService.determinerRole().equals("admin"))
    	    		{
    	    			while(true) {

    	    			System.out.println("---------------------------------------------------------------");
    	    			System.out.println("pour Afficher la liste de tous les mat�riels saisir 1 ");
        	    		System.out.println("pour chercher un materiel saisir 2 ");
        	    		System.out.println("pour  Cr�er un nouveau mat�riel saisir 3 ");
        	    		System.out.println("pour Supprimer un mat�riel saisir 4 ");
        	    		System.out.println("pour Modifier les informations d'un mat�riel saisir 5 ");
        	    		System.out.println("pour Marquer un mat�riel indisponible saisir 6 ");
        	    		System.out.println("pour Allouer un mat�riel saisir 7 ");
        	    		System.out.println("pour rendre un materiele saisir 8 ");
        	    		System.out.println("pour Afficher la liste des mat�riels allou�s  saisir 9 ");
        	    		System.out.println("pour Afficher la liste des mat�riels allou�s par chaque utilisateur saisir 10 ");
        	    		
        	    		
        	    		System.out.println("---------------------------------------------------------------");
  
        	    		String choix = scanner.next();
        	    		if(choix.equals("1")) {
        	    			gestionMaterielService.listerMateriel();
        	    		}
        	    		else if(choix.equals("2")) {
        	        		System.out.println(" saisir id : ");
        	        		choix = scanner.next();
        	    			gestionMaterielService.findMateriel(Long.parseLong(choix));
        	    		}   
        	    		else if (choix.equals("3")) {
        	    		
        	    			/*System.out.println(" saisir le nom du materiel  : ");
        	    			String m1 = scanner.next();
        	    			System.out.println(" saisir le code de materiel : ");
        	    			String m2 = scanner.next();
        	    			M.setName(m1);
        	    			M.setCode(m2);
        	    			gestionMaterielService.ajouterNouveauMateriel(M);
        	    			publisher.publish(new MyEvent<>(M, EventType.ADD));*/
        	    			
        	    			
        	    			System.out.println("Pour ajouter un livre saisir (1) sinon pour ajouter une chaise saisir (2)");
                			String input = scanner.next();
                			
                				if(input.equals("1")) {
                					Materiel materiel = new Livre();
                				
                					System.out.println("Veuillez entrer le code du livre : ");
                					String codeMat =  scanner.next();
                					materiel.setCode(codeMat);
                					materiel.setName("Livre");
                					gestionMaterielService.ajouterNouveauMateriel(materiel);
                					publisher.publish(new MyEvent<>(materiel, EventType.ADD));
                				}
                				
                				else if (input.equals("2")) {
                					Materiel materiel = new Chaise();
                					System.out.println("Veuillez entre le code de la chaise");
                					String codeMat= scanner.next();
                					materiel.setCode(codeMat);
                					materiel.setName("Chaise");
                					

                					gestionMaterielService.ajouterNouveauMateriel(materiel);
                					publisher.publish(new MyEvent<>(materiel, EventType.ADD));

                					
                					
                					
                				}
                				
                				
                				
                				else {
                					System.out .println("Choix invalide !");
                					
                				}
        	    		}
        	    		else if  (choix.equals("4")) {
        	    		
        	    			System.out.println(" saisir L'id du materiel  : ");
        	    			Long id = Long.parseLong(scanner.next());
        	    			M.setId(id);
        	    			gestionMaterielService.supprimerMateriel(M );
        	    			publisher.publish(new MyEvent<>(M, EventType.REMOVE));
        	    		}
        	    		else if (choix.equals("5")) {
        	    		
        	    		System.out.println(" saisir L'id du materiel que vous souhaitez modifier : ");
        	        	Long id =  Long.parseLong( scanner.next());
      	    			System.out.println(" saisir le nouveau nom du materiel  : ");
      	    			String nvName = scanner.next();
      	    			System.out.println(" saisir le nouveau code de materiel : ");
      	    			String nvCode = scanner.next();
      	    			M.setId(id);
      	    			M.setName(nvName);
      	    			M.setCode(nvCode);
      	    			gestionMaterielService.modifierInfosMateriel(M);
      	    			publisher.publish(new MyEvent<>(M, EventType.UPDATE));
        	    		}
    	    			
        	    		else if (choix.equals("6")) 
        	    		{
        	    			System.out.println(" saisir L'id du materiel que vous souhaitez rendre indisponible : ");
        	        		Long id =  Long.parseLong( scanner.next());
        	        		gestionMaterielService.indisponibleMateriel(id);
        	    			
        	    		}
        	    		else if (choix.equals("7")) {
        	    			System.out.println("saisir le nom du materile � allouer : ");
        	    		   String m1 = scanner.next();
        	    			System.out.println("saisir la duree d'allocation (par jour): ");
        	    			String m2 = scanner.next();
        	    			gestionPersonneService.allouerMateriel(m1,m2);
        	    		}
        	    		else if (choix.equals("8")) 
        	    		{
        	    			System.out.println("saisir le nom du materile � rendre : ");
         	    		   String m = scanner.next();
         	    		  gestionPersonneService.rendreMateriel(m);
         	    		   
        	    		}
        	    		else if (choix.equals("9")) 
        	    		{
        	    			
          	    			gestionMaterielService.listerMaterielAlloue();
        	    			
        	    		}
        	    		else if(choix.equals("10")){
        	    		
        	    			gestionMaterielService.afficherMaterielAllouerParUtilisateur();
        	    		}
        	    		
        	    		else {
        	    			System.out.println("Le choix est invalide !");
        	    		}
	
    	    		}
    	    			
    	    			
    	    			
    	    			
    	    		} 
    	    		else {
    	    			while(true) {
    	    			
    	    			System.out.println("---------------------------------------------------------------");
    	    			
    	    			System.out.println("pour afficher la liste de tous les mat�riels saisir 1 ");        	    		
        	    		System.out.println("pour  Chercher un mat�riel saisir 2 ");
        	    		System.out.println("pour allouer un mat�riel saisir 3 ");
        	    		System.out.println("pour  rendre un mat�riel saisir 4 ");
        	    		System.out.println("pour afficher la liste des mat�riels allou�s saisir 5");
        	    		
        	    		System.out.println("---------------------------------------------------------------");
        	    		
        	    		String choix = scanner.next();
        	    		if(choix.equals("1")) {
        	    			gestionMaterielService.listerMateriel();
        	    		}
        	    		else if(choix.equals("2")) {
        	        		System.out.println(" saisir id : ");
        	        		choix = scanner.next();
        	    			gestionMaterielService.findMateriel(Long.parseLong(choix));
        	    		}
        	    		else if (choix.equals("3")) {
        	    			System.out.println("saisir le nom du materile � allouer : ");
        	    		   String m1 = scanner.next();
        	    			System.out.println("saisir la duree d'allocation (par jour): ");
        	    			String m2 = scanner.next();
        	    			gestionPersonneService.allouerMateriel(m1,m2);
        	    		}
        	    		else if (choix.equals("4")) 
        	    		{
        	    			System.out.println("saisir le nom du materile � rendre : ");
         	    		   String m = scanner.next();
         	    		  gestionPersonneService.rendreMateriel(m);
         	    		   
        	    		}
        	    		else if (choix.equals("5")) 
        	    		{
        	    		
          	    			gestionMaterielService.listerMaterielAlloue();
        	    			
        	    		}
        	    		else {
        	    			System.out.println("Le choix est invalide !");
        	    		}
    	    		}
    	    			
    	    		}
    	    		
    	    	 	    		
	    }



}
