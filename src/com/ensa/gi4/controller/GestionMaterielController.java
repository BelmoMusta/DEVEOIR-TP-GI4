package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
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
       String code;
       System.out.println("Veuillez entrer votre mot de passe");
       String password = scanner.next();
       if(gestionUser.connexion(name, password)!=null){
    	   
       
       	 if(gestionUser.isAdmin(name, password)){
                //**********************Scenario Pour Admin*****************************
       		 
	       		 
       		 
       		 while(true) {
       			 
       			   System.out.println("Pour liseter les materiels, saisir 1: ");
        			System.out.println("Pour chercher un materiel,saisir 2: ");
        			System.out.println("Pour ajouter un nouveau materiel,saisir 3: ");
        			System.out.println("Pour supprimer un matériel, saisir 4 ");
    	    		System.out.println("Pour modifier un matériel, saisir 5 ");
    	    		System.out.println("Pour allouer un materiel saisir 6: ");
           			System.out.println("Pour rendre un materiel saisir 7: ");
           			System.out.println("Pour lister les materiels alloués saisir 8: ");
        			
        			String num = scanner.next();
           			
           			if(num.equals("1")) {
            			gestionMateriel.listerMateriel();
            		    System.out.println(" ");
            			System.out.println("******************************");
            		}
           			
            		else if(num.equals("2")) {
                		System.out.println("entrer son id : ");
                		String id = scanner.next();
            			gestionMateriel.findOneMateriel(Long.parseLong(id));
            		    System.out.println(" ");

            			System.out.println("******************************");

            		}
           			//ajout
            		else if (num.equals("3")) {
            			System.out.println("Pour ajouter un livre saisir (1) sinon pour ajouter une chaise saisir (2)");
            			String input = scanner.next();
            			
            				if(input.equals("1")) {
            					Materiel materiel = new Livre();
            				
            					System.out.println("Veuillez entrer le code du livre : ");
            					String codeMat =  scanner.next();
            					materiel.setCode(codeMat);
            					materiel.setName("Livre");
            					gestionMateriel.ajouterNouveauMateriel(materiel);
            					publisher.publish(new MyEvent<>(materiel, EventType.ADD));
            				}
            				
            				else if (input.equals("2")) {
            					Materiel materiel = new Chaise();
            					System.out.println("Veuillez entre le code de la chaise");
            					String codeMat= scanner.next();
            					materiel.setCode(codeMat);
            					materiel.setName("Chaise");
            					

            					gestionMateriel.ajouterNouveauMateriel(materiel);
            					publisher.publish(new MyEvent<>(materiel, EventType.ADD));

            					
            					
            					
            				}
            				
            				
            				
            				else {
            					System.out .println("Choix invalide !");
            					
            				}
            		}
           		//supprimer
    				else if(num.equals("4")) {
    					System.out.println("Veuillez saisir l'id du matériel à supprimer ");
    	    			int id = scanner.nextInt();
    	    			if(gestionMateriel.findOneMaterielINT(id) != 0)
    	    			{
    	    				gestionMateriel.supprimerMateriel(id);

    	    			
    	    			}
    	    			else {
    	    				System.out.println("le materiel que vous voulez supprimer n'existe pas");
    	    			}
    				}
           			
           			//modifier 
    				else if(num.equals("5")) {
    					
    					System.out.println("Veuillez saisir l'id du matériel à modifier ");
    	    			int id = scanner.nextInt();
    	    			
    	    			if(gestionMateriel.findOneMaterielINT(id) != 0) {
    	    				
    	    				System.out.println("Veuillez saisir le nouveau nom du matériel ");
    	    				name = scanner.next();
    	    				System.out.println("Veuillez saisir le nouveau code du matériel ");
    	    				code = scanner.next();
    	    				gestionMateriel.modifierMateriel(id,name,code);
    	    			}
    	    			else {
    	    				System.out.println("le materiel que vous vulez supprimer n'existe pas");
    	    			}
    				}
           			
           			//allocation
    				else if(num.equals("6")) {
    					System.out.println("entrer le code du materiel ");
           			 code = scanner.next();
           			System.out.println("entrer la durée d'allocation ");
           			String duree = scanner.next();
           			gestionUser.allouerMateriel(code, duree);
           		    System.out.println(" ");

           			System.out.println("******************************");
    				}
           			
           			//rendre
    				else if(num.equals("7")) {
    					
    					System.out.println("Saisir l'id du matériel à rendre");
    	    			int id=scanner.nextInt();
    	    			gestionUser.rendreMateriel(id);
            		}
           			 //liste allouer
            		else if (num.equals("8")) {
            			System.out.println("Les Materiels alloués sont: ");
            			
            			gestionUser.listeMaterielAlloue(name);
            			System.out.println("");
            			System.out.println("******************************");

            		}
            		else {
            			System.out.println("Choix invalide !");
            		}
       		 }
       	 }
       	 else {
       		 
       		 //********************Scenario pour les employes ****************************
       		 
       		 while(true) {
       			System.out.println("Pour lister les materiels, saisir 1: ");
       			System.out.println("Pour chercher un materiel,saisir 2: ");
       			System.out.println("Pour allouer un materiel saisir 3: ");
       			System.out.println("Pour rendre un materiel saisir 4: ");
       			System.out.println("Pour lister les materiels alloués saisir 5: ");



       		   String num = scanner.next();
       			
       			if(num.equals("1")) {
        			gestionMateriel.listerMateriel();
        		    System.out.println(" ");
        			System.out.println("******************************");
        		}
       			
        		else if(num.equals("2")) {
            		System.out.println("entrer son id : ");
            		String id = scanner.next();
        			gestionMateriel.findOneMateriel(Long.parseLong(id));
        		    System.out.println(" ");

        			System.out.println("******************************");

        		}
       			
        		else if(num.equals("3")) {
        			System.out.println("entrer le code du materiel ");
        			 code = scanner.next();
        			System.out.println("entrer la durée d'allocation ");
        			String duree = scanner.next();
        			gestionUser.allouerMateriel(code, duree);
        		    System.out.println(" ");

        			System.out.println("******************************");

        		}
        		
        		else if(num.equals("4")) {
        			System.out.println("Saisir l'id du matériel à rendre");
	    			int id=scanner.nextInt();
	    			gestionUser.rendreMateriel(id);
        		}
       			
        		else if (num.equals("5")) {
        			System.out.println("Les Materiels alloués sont: ");
        			
        			gestionUser.listeMaterielAlloue(name);
        			System.out.println("");
        			System.out.println("******************************");

        		}
       		 }
       	 }
       }
      
       
       else {
    	   System.out.println("");
    	   System.out.println("**************************************");

     	  System.out.println("Pour sortir de l'application saisir 0: ");
     	  System.out.println("Pou réessayer à nouveau saisir 1: ");
     	  String num = scanner.next();
     	  if(num.equals("0")) {
     		  sortirDeLApplication();
     	  }else{
     		  name=null;
     		  password=null;
     		 afficherMenu();
     	  }
       }
         
        // publisher.publish(new MyEvent<>(new Livre(), EventType.ADD));
     }

     private void sortirDeLApplication() {
     	System.out.print("Merci pour votre visite");
         System.exit(0);
     }

}
