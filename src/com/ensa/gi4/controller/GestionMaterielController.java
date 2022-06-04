package com.ensa.gi4.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.modele.TypeMateriel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.AuthentificationService;
import com.ensa.gi4.service.api.GestionMaterielServiceFacade;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired
    ApplicationPublisher publisher;
    @Autowired
    AuthentificationService authentificationService; 
    @Autowired
    GestionMaterielServiceFacade gestionMaterielServiceFacade; 
    
    Scanner scanner  = new Scanner(System.in);
    
    public User authentification(List<String> userData) {
    	return authentificationService.login(userData); 
    }
    
    public List<String> getUserData(){
    	String username; 
    	String password; 
    	List<String> userData = new ArrayList<String>();  
    	
    	System.out.println("*****************************************************");
    	System.out.println("\tBienvenu dans notre l'application de gestion et allocation des materiels !");
    	System.out.println("\tPour utiliser beneficier de notre service veuiller vous identifier ");
    	System.out.println("*****************************************************");
    	
    	System.out.print("Entrer votre nom d'utilisateur : ");
    	username = scanner.next(); 
    	System.out.print("Entrer votre mot de passe  : ");
    	password = scanner.next(); 
    	
    	if (Optional.of(username).isPresent() && Optional.of(password).isPresent()) {
    		userData.add(username); 
    		userData.add(password); 
    	}
    	
    	return userData; 
    		
    }

    public void afficherMenu(User user) {
    	
    	switch (user.getRole()) {
    	
		case ADMIN:
			// Menu Admin
			
			System.out.println("1- Chercher materiel ");
			System.out.println("2- Ajouter materiel ");
			System.out.println("3- Supprimer materiel ");
	        System.out.println("4- Modifier materiel ");
	        System.out.println("5- Marquer un matériel indisponible "); 
	        System.out.println("6- Allouer materiel "); 
	        System.out.println("7- Rendre materiel  "); 
	        System.out.println("8- Afficher la liste de mes materiels allouées ");
	        System.out.println("9- Afficher la liste des materiels allouées par chaque utilisateur ");
	        System.out.println("10- Afficher la liste des materiels ");
	        System.out.println("0- Pour sortir de l'application, entrer 0");
	        
	        Scanner scanner = new Scanner(System.in);
	        String operation = scanner.next();
	        String mat;
	        
	        switch(Optional.of(operation).get()) {
	        
	        case "0":
	        	sortirDeLApplication();
	        	break;
	        case "1":
	        	/*System.out.print("1-pour livre \t 2-pour chaise : ");
	            mat = scanner.next();
	            Optional<String> checkType1 = Optional.of(mat); 
	            
	            if (checkType1.isPresent()) {
	    
	            	if(checkType1.get().equals("1")) {
	                 	gestionMaterielServiceFacade.afficherMateriel(TypeMateriel.LIVRE);
	                 }
	            	else if(checkType1.get().equals("2")) {
	                 	gestionMaterielServiceFacade.afficherMateriel(TypeMateriel.CHAISE);
	                 }
	            	else {
	                	 System.out.println("Le type " + checkType1.get() + " n'est pas reconnu ");
					}
				}else {
					System.out.println("Choix invalide ! ");
				}*/
	            break;
	        case "2":
	        	
	        	System.out.print("1-pour livre \t 2-pour chaise : ");
	            mat = scanner.next();
	            Optional<String> checkType = Optional.of(mat); 
	            
	            if (checkType.isPresent()) {

	                	System.out.print("Saisir le code  : ");
	                    String code = scanner.next(); 
	                    Optional<String> checkMaterialCode = Optional.of(code); 
	                    System.out.print("Saisir le nombre de stock : ");
	                    Integer  stock = scanner.nextInt(); 
	                    Optional<Integer> checkMaterialStock = Optional.of(stock); 
	                    
	                    if (checkMaterialCode.isPresent() && checkMaterialStock.isPresent()) {

	                    	if(checkType.get().equals("1")) {
	                        	gestionMaterielServiceFacade.ajouterNouveauMateriel(TypeMateriel.LIVRE, checkMaterialCode.get(), checkMaterialStock.get());
	                        }
	                    	else if(checkType.get().equals("2")) {
	                        	gestionMaterielServiceFacade.ajouterNouveauMateriel(TypeMateriel.CHAISE, checkMaterialCode.get(), checkMaterialStock.get());
	                        }
	                    	else {
	        					System.out.println("Le type " + mat + " n'est pas reconnu ");
	        				}
						}
	                    else {
							System.out.println("Code du materiel invalide, ajout non effectu�");
						}
	                    
				}else {
					System.out.println("Choix invalide ! ");
				}
	        	
	            break;
	            
	        case "3":
	        	System.out.print("1-pour livre \t 2-pour chaise : ");
	            mat = scanner.next();
	            Optional<String> checkType4 = Optional.of(mat); 
	            
	            if (checkType4.isPresent()) {
	            	
	            	System.out.print("Saisir le code du materiel à supprimer : ");
	                String code = scanner.next(); 
	                Optional<String> checkMaterialCode = Optional.of(code); 
	                
	                if (checkMaterialCode.isPresent()) {

	                	if(checkType4.get().equals("1")) {
	                    	gestionMaterielServiceFacade.supprimerMateriel(TypeMateriel.LIVRE, checkMaterialCode.get());
	                    }
	                	else if(checkType4.get().equals("2")) {
	                    	gestionMaterielServiceFacade.supprimerMateriel(TypeMateriel.CHAISE, checkMaterialCode.get());
	                    }
	                	else {
	    					System.out.println("Le type " + mat + " n'est pas reconnu ");
	    				}
					}
	                else {
						System.out.println("Id du materiel invalide, Suppression non effectuée");
					}
				}else {
					System.out.println("Choix invalide ! ");
				}
	            break;
	        
	            
	        case "4":
	        	System.out.print("1-pour livre \t 2-pour chaise : ");
	            mat = scanner.next();
	            Optional<String> checkType3 = Optional.of(mat); 
	            
	            if (checkType3.isPresent()) {
	            	
	            	System.out.print("Saisir le code du materiel à modifier : ");
	                String ancienCode = scanner.next(); 
	                Optional<String> checkMaterialAncienCode = Optional.of(ancienCode); 
	                
	                System.out.print("Saisir le nouveau code du materiel : ");
	                String code = scanner.next(); 
	                Optional<String> checkMaterialCode = Optional.of(code); 
	                
	                System.out.print("Saisir le nouveau stock du materiel : ");
	                Integer stock = scanner.nextInt(); 
	                Optional<Integer> checkMaterialstock = Optional.of(stock); 
	                
	                if (checkMaterialAncienCode.isPresent() && checkMaterialstock.isPresent() && checkMaterialCode.isPresent()) {
	                	
	                	if (checkMaterialCode.isPresent()) {
	                		if(checkType3.get().equals("1")) {
	                        	gestionMaterielServiceFacade.modifierMateriel(TypeMateriel.LIVRE, checkMaterialCode.get(), checkMaterialstock.get(), checkMaterialAncienCode.get());
	                        }
	                    	else if(checkType3.get().equals("2")) {
	                        	gestionMaterielServiceFacade.modifierMateriel(TypeMateriel.CHAISE, checkMaterialCode.get(), checkMaterialstock.get(), checkMaterialAncienCode.get());
	                        }
	                    	else {
	        					System.out.println("Le type " + mat + " n'est pas reconnu ");
	        				}
						}

					}
	                else {
						System.out.println("Id du materiel invalide, modification non effectuée");
					}
				}else {
					System.out.println("Choix invalide ! ");
				}
	            break;
	            
	        case "5":
	        	System.out.print("1-pour livre \t 2-pour chaise : ");
	            mat = scanner.next();
	            Optional<String> checkType5 = Optional.of(mat); 
	            
	            if (checkType5.isPresent()) {
	            	
	            	System.out.print("Saisir le code du materiel à rendre indisponible : ");
	                String code = scanner.next(); 
	                Optional<String> checkMaterialCode = Optional.of(code); 
	                
	                if (checkMaterialCode.isPresent()) {

	                	if(checkType5.get().equals("1")) {
	                    	gestionMaterielServiceFacade.materielIndisponible(TypeMateriel.LIVRE, checkMaterialCode.get());
	                    }
	                	else if(checkType5.get().equals("2")) {
	                    	gestionMaterielServiceFacade.materielIndisponible(TypeMateriel.CHAISE, checkMaterialCode.get());
	                    }
	                	else {
	    					System.out.println("Le type " + mat + " n'est pas reconnu ");
	    				}
					}
	                else {
						System.out.println("Code du materiel invalide, recherche non effectuée");
					}
				}else {
					System.out.println("Choix invalide ! ");
				}
	            break;
	                      
	        
	       /*  
	        case "5":
	        	System.out.print("1-pour livre \t 2-pour chaise : ");
	            mat = scanner.next();
	            Optional<String> checkType5 = Optional.of(mat); 
	            
	            if (checkType5.isPresent()) {
	            	
	            	System.out.print("Saisir ID du materiel à chercher : ");
	                Integer id = scanner.nextInt(); 
	                Optional<Integer> checkMaterialCode = Optional.of(id); 
	                
	                if (checkMaterialCode.isPresent()) {

	                	if(checkType5.get().equals("1")) {
	                    	gestionMaterielServiceFacade.chercherMateriel(TypeMateriel.LIVRE, id);;
	                    }
	                	else if(checkType5.get().equals("2")) {
	                    	gestionMaterielServiceFacade.chercherMateriel(TypeMateriel.CHAISE, id);;
	                    }
	                	else {
	    					System.out.println("Le type " + mat + " n'est pas reconnu ");
	    				}
					}
	                else {
						System.out.println("Code du materiel invalide, recherche non effectuée");
					}
				}else {
					System.out.println("Choix invalide ! ");
				}
	            break;
	            
	        case "10":
	        	gestionMaterielServiceFacade.listeMateriels();
	            break; */  
	        default:
	        	System.out.println("choix invalide");
	        	break;
	        }  
			
			break;
		case USER : 
			// Menu User

	    	
			
			break;
		default:
			break;
		}
       
        //publisher.publish(new MyEvent<>(new Livre(), EventType.ADD));
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
