package com.ensa.gi4.controller;

import com.ensa.gi4.datatabase.api.UtilisateurDAO;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionUtilisateurService;

import resources.MessagesGestionMaterielController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.List;
import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController implements MessagesGestionMaterielController{

    @Autowired

    ApplicationPublisher publisher;
    
    @Autowired
    GestionUtilisateurService userService;
    
    @Autowired
    GestionMaterielService materielService;

    @Autowired
    MessagesGestionMaterielController message;
    
    public void afficherMenu() {
    	
    	//Les variables 
    	int reponse = 0;
    	int check = 0;
    	Utilisateur utilisateur = null, checkUtilisateur = null;
    	Materiel materiel = null;
    	List<Materiel> listMaterielUser = null;
    	List<Materiel> listMaterielEveryUser = null;
    	List<Materiel> listMateriel = null;
    	
    	//Message de réception
    	System.out.println(message.welcomeMessage);
    	
    	//Gérer l'authentification
    	while(checkUtilisateur.equals(null)) {
    		
    		do {
    			
    			System.out.print(message.choiceMessage);
            	Scanner scanner = new Scanner(System.in);
            	reponse = scanner.nextInt();
    			
    		}while(reponse != 0 && reponse != 1);
    		
    		if(reponse == 0) {
    			
    			sortirDeLApplication();
    		}
    		
    		if(reponse == 1) {
    			
    			System.out.print(message.saisirUserName);
    			Scanner scannerUserName = new Scanner(System.in);
    			String username = scannerUserName.nextLine();
    			
    			System.out.print(message.saisirPassword);
    			Scanner scannerPassword = new Scanner(System.in);
    			String password = scannerPassword.nextLine();
    			
    			utilisateur.setUsername(username);
    			utilisateur.setPassword(password);
        		
    			checkUtilisateur = userService.loginS(utilisateur);
    		}
    			
    	}
    	
    	//Le traitement dans le cas où l'utilisateur est authentifié
    	if(!checkUtilisateur.equals(null)) {
    		
    		String role = checkUtilisateur.getRole();
    		
    		//Traiter les roles de l'utilisateur
    		//On aura 3 cas possibles : Administrateur, Employe, Administrateur et Employe
    		
    		//Diviser le role en utilisant le délimiteur ,
    		String[] roles = role.split(",");
    		
    		//La taille de roles
    		int sizeRoles = roles.length;
    		
    		//Traiter les cas :
    		
    		//Un seul role effectué :
    		if(sizeRoles == 1) {
    			
    			for(int i = 0 ; i < roles.length ; i++) {
    				
    				//Traiter le cas d'un administrateur
    				if(roles[i].equals("Administrateur")) {
    					
    					System.out.println(message.welcomeMessageAdmin);		
    					
    					System.out.println(message.quitterApp);
    					System.out.println(message.chercherMateriel);
    					System.out.println(message.ajouterNouveauMateriel);
    					System.out.println(message.supprimerMateriel);
    					System.out.println(message.modifierMateriel);
    					System.out.println(message.materielIndisponible);
    					System.out.println(message.allouerMateriel);
    					System.out.println(message.rendreMateriel);
    					System.out.println(message.listMaterielUser);
    					System.out.println(message.listMaterielEveryUser);
    					System.out.println(message.listMateriel);
    					
    					System.out.print(message.saisirNumFonction);
    					Scanner scannerNumero = new Scanner(System.in);
    					int numero = scannerNumero.nextInt();
    					
    					//Le switch sur le numéro
    					switch(numero) {
    					
    					case 0:
    						sortirDeLApplication();
    						break;
    						
    					case 1: 
    						System.out.print(message.taperIdentifiantChercher);
    						Scanner sc = new Scanner(System.in);
    						Long identifiant = sc.nextLong();
    						
    						//L'éxécution de la méthode
    						materiel = materielService.chercherMaterielS(identifiant);
    						break;
    						
    					case 2 :
    						System.out.println(message.taperNomMaterielAjout);
    						Scanner scannerNom = new Scanner(System.in);
    						String nomMateriel = scannerNom.nextLine();
    						
    						System.out.print(message.taperCodeMaterielAjout);
    						Scanner scannerCode = new Scanner(System.in);
    						String codeMateriel = scannerCode.nextLine();
    						
    						System.out.print(message.taperEtatMaterielAjout);
    						Scanner scannerEtat = new Scanner(System.in);
    						String etatMateriel = scannerEtat.nextLine();
    						
    						materiel.setName(nomMateriel);
    						materiel.setCode(codeMateriel);
    						materiel.setCode(etatMateriel);
    						
    						//L'éxécution de la méthode
    						check = materielService.ajouterNouveauMaterielS(materiel);
    						break;
    						
    					case 3 : 
    						
    						System.out.print(message.taperIdentifinantSupprimer);
    						Scanner scannerSupprimer = new Scanner(System.in);
    						Long identifiantSupprimer = scannerSupprimer.nextLong();
    						
    						//L'éxécution de la méthode
    						check = materielService.supprimerMaterielS(identifiantSupprimer);
    						break;
    						
    					case 4 : 
    						
    						System.out.print(message.taperIdentifinantModifier);
    						Scanner idM = new Scanner(System.in);
    						Long identifiantModifier = idM.nextLong();
    						
    						System.out.println(message.taperNomModifier);
    						Scanner scannerNomM = new Scanner(System.in);
    						String nomMaterielM = scannerNomM.nextLine();
    						
    						System.out.print(message.taperEtatModifier);
    						Scanner scannerEtatM = new Scanner(System.in);
    						String etatMaterielM = scannerEtatM.nextLine();
    						
    						materiel.setName(nomMaterielM);
    						materiel.setCode(etatMaterielM);
    						
    						//L'éxécution de la méthode
    						check = materielService.modifierMaterielS(materiel, identifiantModifier);
    						break;
    						
    					case 5 :
    						
    						System.out.print(message.taperIdentifinantModifier);
    						Scanner idMI = new Scanner(System.in);
    						Long identifiantModifierI = idMI.nextLong();
    						//L'éxécution de la méthode 
    						check = materielService.materielIndisponibleS(identifiantModifierI, "Indisponible");
    						break;
    						
    					case 6 :
    						
    						System.out.print(message.allouerMateriel);
    						Scanner scAllouer = new Scanner(System.in);
    						Long idAllouer = scAllouer.nextLong();
    						
    						//L'éxécution de la méthode 
    						check = materielService.allouerMaterielS(idAllouer, "Alloue", checkUtilisateur.getIdUser());
    						break;
    						
    					case 7 :
    						
    						System.out.print(message.taperIdentifiantRendre);
    						Scanner scRendre = new Scanner(System.in);
    						Long idRendre = scRendre.nextLong();
    						
    						//L'éxécution de la méthode
    						check = materielService.rendreMaterielS(idRendre, "Restitue");
    						break;
    					
    					case 8 :
    						
    						System.out.println(message.listMaterielUser1);
    						
    						//L'éxécution de la méthode
    						listMaterielUser = materielService.afficherMaterielUserS(checkUtilisateur.getIdUser());
    						
    						for(Materiel item : listMaterielUser) {
    							
    							System.out.println(item.getName() + " " + item.getCode());
    						}
    						break;
    						
    					case 9 : 
    						
    						System.out.println(message.listMaterielEveryUser1);
    						
    						//L'éxécution de la méhtode 
    						listMaterielEveryUser = materielService.afficherMaterielEveryUserS();
    						
    						for(Materiel item : listMaterielEveryUser) {
    							
    							System.out.println(item.getName() + " " + item.getCode());
    						}
    						break;
    						
    					case 10 : 
    						
    						System.out.println(message.listMateriel);
    						
    						//L'éxécution de la méthode 
    						listMateriel = materielService.afficherMaterielS();
    						
    						for(Materiel item : listMateriel) {
    							
    							System.out.println(item.getName() + " " + item.getCode());
    						}
    						break;
    						
    					default : 
    						
    						System.out.println(message.saisirNombre0to10);
    					}
    					
    				}
    				
    				//Traiter le cas d'un employé
    				if(roles[i].equals("Employe")) {
    					
    					System.out.println(message.welcomeMessageEmploye);
    					
    					System.out.println(message.quitterApp);
    					System.out.println(message.chercherMateriel);
    					System.out.println(message.allouerMateriel);
    					System.out.println(message.rendreMateriel);
    					System.out.println(message.listMaterielUser1);
    					System.out.println(message.listMateriel);
    					
    					System.out.print(message.saisirNumFonction);
    					Scanner scannerNumero = new Scanner(System.in);
    					int numero = scannerNumero.nextInt();
    					
    					switch(numero) {
    					
    					case 0 :
    						
    						sortirDeLApplication();
    						break;
    						
    					case 1: 
    						
    						System.out.print(message.taperIdentifiantChercher);
    						Scanner sc = new Scanner(System.in);
    						Long identifiant = sc.nextLong();
    						
    						//L'éxécution de la méthode
    						materiel = materielService.chercherMaterielS(identifiant);
    						break;
    						
    					case 2 :
    						
    						System.out.print(message.taperIdentifiantAllouer);
    						Scanner scAllouer = new Scanner(System.in);
    						Long idAllouer = scAllouer.nextLong();
    						
    						//L'éxécution de la méthode 
    						check = materielService.allouerMaterielS(idAllouer, "Alloue", checkUtilisateur.getIdUser());
    						break;
    						
    					case 3 :
    						
    						System.out.print(message.taperIdentifiantRendre);
    						Scanner scRendre = new Scanner(System.in);
    						Long idRendre = scRendre.nextLong();
    						
    						//L'éxécution de la méthode
    						check = materielService.rendreMaterielS(idRendre, "Restitue");
    						break;
    						
    					case 4 :
    						
    						System.out.println(message.listMaterielUser1);
    						
    						//L'éxécution de la méthode
    						listMaterielUser = materielService.afficherMaterielUserS(checkUtilisateur.getIdUser());
    						
    						for(Materiel item : listMaterielUser) {
    							
    							System.out.println(item.getName() + " " + item.getCode());
    						}
    						break;
    						
    					case 5 :
    						
    						System.out.println(message.listMateriel);
    						
    						//L'éxécution de la méthode 
    						listMateriel = materielService.afficherMaterielS();
    						
    						for(Materiel item : listMateriel) {
    							
    							System.out.println(item.getName() + " " + item.getCode());
    						}
    						break;
    						
    					default : 
    						
    						System.out.println(message.saisirNombre0to5);
    					}
    					
    				}
    			}
    				
    				
    		}
    		
    		//Deux roles en même temps
    		if(sizeRoles == 2) {
    			
    			System.out.println(message.welcomeMessageMixte);
    			
    			System.out.println(message.quitterApp);
				System.out.println(message.chercherMateriel);
				System.out.println(message.ajouterNouveauMateriel);
				System.out.println(message.supprimerMateriel);
				System.out.println(message.modifierMateriel);
				System.out.println(message.materielIndisponible);
				System.out.println(message.allouerMateriel);
				System.out.println(message.rendreMateriel);
				System.out.println(message.listMaterielUser);
				System.out.println(message.listMaterielEveryUser);
				System.out.println(message.listMateriel);
				
				System.out.print(message.saisirNumFonction);
				Scanner scannerNumero = new Scanner(System.in);
				int numero = scannerNumero.nextInt();
				
				//Le switch sur le numéro
				switch(numero) {
				
				case 0:
					sortirDeLApplication();
					break;
					
				case 1: 
					System.out.print(message.taperIdentifiantChercher);
					Scanner sc = new Scanner(System.in);
					Long identifiant = sc.nextLong();
					
					//L'éxécution de la méthode
					materiel = materielService.chercherMaterielS(identifiant);
					break;
					
				case 2 :
					System.out.println(message.taperNomMaterielAjout);
					Scanner scannerNom = new Scanner(System.in);
					String nomMateriel = scannerNom.nextLine();
					
					System.out.print(message.taperCodeMaterielAjout);
					Scanner scannerCode = new Scanner(System.in);
					String codeMateriel = scannerCode.nextLine();
					
					System.out.print(message.taperEtatMaterielAjout);
					Scanner scannerEtat = new Scanner(System.in);
					String etatMateriel = scannerEtat.nextLine();
					
					materiel.setName(nomMateriel);
					materiel.setCode(codeMateriel);
					materiel.setCode(etatMateriel);
					
					//L'éxécution de la méthode
					check = materielService.ajouterNouveauMaterielS(materiel);
					break;
					
				case 3 : 
					
					System.out.print(message.taperIdentifinantSupprimer);
					Scanner scannerSupprimer = new Scanner(System.in);
					Long identifiantSupprimer = scannerSupprimer.nextLong();
					
					//L'éxécution de la méthode
					check = materielService.supprimerMaterielS(identifiantSupprimer);
					break;
					
				case 4 : 
					
					System.out.print(message.taperIdentifinantModifier);
					Scanner idM = new Scanner(System.in);
					Long identifiantModifier = idM.nextLong();
					
					System.out.print(message.taperNomModifier);
					Scanner scannerNomM = new Scanner(System.in);
					String nomMaterielM = scannerNomM.nextLine();
					
					System.out.print(message.taperEtatModifier);
					Scanner scannerEtatM = new Scanner(System.in);
					String etatMaterielM = scannerEtatM.nextLine();
					
					materiel.setName(nomMaterielM);
					materiel.setCode(etatMaterielM);
					
					//L'éxécution de la méthode
					check = materielService.modifierMaterielS(materiel, identifiantModifier);
					break;
					
				case 5 :
					
					System.out.print(message.taperIdentifinantModifier);
					Scanner idMI = new Scanner(System.in);
					Long identifiantModifierI = idMI.nextLong();
					//L'éxécution de la méthode 
					check = materielService.materielIndisponibleS(identifiantModifierI, "Indisponible");
					break;
					
				case 6 :
					
					System.out.print(message.allouerMateriel);
					Scanner scAllouer = new Scanner(System.in);
					Long idAllouer = scAllouer.nextLong();
					
					//L'éxécution de la méthode 
					check = materielService.allouerMaterielS(idAllouer, "Alloue", checkUtilisateur.getIdUser());
					break;
					
				case 7 :
					
					System.out.print(message.rendreMateriel);
					Scanner scRendre = new Scanner(System.in);
					Long idRendre = scRendre.nextLong();
					
					//L'éxécution de la méthode
					check = materielService.rendreMaterielS(idRendre, "Restitue");
					break;
				
				case 8 :
					
					System.out.println(message.listMaterielUser1);
					
					//L'éxécution de la méthode
					listMaterielUser = materielService.afficherMaterielUserS(checkUtilisateur.getIdUser());
					
					for(Materiel item : listMaterielUser) {
						
						System.out.println(item.getName() + " " + item.getCode());
					}
					break;
					
				case 9 : 
					
					System.out.println(message.listMaterielEveryUser1);
					
					//L'éxécution de la méhtode 
					listMaterielEveryUser = materielService.afficherMaterielEveryUserS();
					
					for(Materiel item : listMaterielEveryUser) {
						
						System.out.println(item.getName() + " " + item.getCode());
					}
					break;
					
				case 10 : 
					
					System.out.println(message.listMateriel);
					
					//L'éxécution de la méthode 
					listMateriel = materielService.afficherMaterielS();
					
					for(Materiel item : listMateriel) {
						
						System.out.println(item.getName() + " " + item.getCode());
					}
					break;
					
				default : 
					
					System.out.println(message.saisirNombre0to10);
				}
    			
    		}
    		
    		
    		
    		
    	}
    		

        publisher.publish(new MyEvent<>(new Livre(), EventType.ADD));
    }

    
    //La méthode pour sortir de l'application
    public void sortirDeLApplication() {
        System.exit(0);
    }

}
