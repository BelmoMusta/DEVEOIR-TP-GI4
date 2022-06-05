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
    String mat;
    
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
	        
	        
	        switch(Optional.of(operation).get()) {
	        
		        case "0":
		        	sortirDeLApplication();
		        	break;        
		        case "1":
		        	chercherMateriel();
		            break;
		            
		        case "2":
		        	ajoutMateriel();
		            break;
		            
		        case "3":
		        	supprimerMateriel();
		            break;
		        
		        case "4":
		        	modifierMateriel();
		            break;
		            
		        case "5":
		        	materielIndisponible();
		            break;
		            
		        case "6":
		        	allouerMateriel(user);
		            break;
		            
		        case "7":
		        	rendreMateriel(user); 	
		            break;
		                      
		        case "8":
		        	afficherMaterielAllouer(user);
		            break;
		        case "9":
		        	gestionMaterielServiceFacade.listeMaterielAlloueParUser(user);
		        	break;
		        case "10":
		        	afficherListeMateriel();
		            break; 
		        default:
		        	System.out.println("choix invalide");
		        	break;
		        }  
			
			break;
		case USER : 
			// Menu User

			System.out.println("1- Chercher materiel ");
	        System.out.println("2- Allouer materiel "); 
	        System.out.println("3- Rendre materiel  "); 
	        System.out.println("4- Afficher la liste de mes materiels allouées ");
	        System.out.println("5- Afficher la liste des materiels ");
	        System.out.println("0- Pour sortir de l'application, entrer 0");
	        
	        Scanner scanner1 = new Scanner(System.in);
	        String operation1 = scanner1.next();
	        
	        
	        switch(Optional.of(operation1).get()) {
	        
		        case "0":
		        	sortirDeLApplication();
		        	break;        
		        case "1":
		        	chercherMateriel();
		            break;
		            
		        case "2":
		        	allouerMateriel(user);
		            break;
		            
		        case "3":
		        	rendreMateriel(user);
		            break;
		        
		        case "4":
		        	afficherMaterielAllouer(user);
		            break;
		            
		        case "5":
		        	afficherListeMateriel();
		            break;
		          
		        default:
		        	System.out.println("choix invalide");
		        	break;
		        }  
	    	
			
			break;
		default:
			System.out.println("Utilisateur invalide ! ");
			break;
		}
       
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }
    
    private void chercherMateriel() {

    	 
    	 System.out.print("1-pour livre \t 2-pour chaise : ");
         mat = scanner.next();
         Optional<String> checkType1 = Optional.of(mat); 
         
         if (checkType1.isPresent()) {
         	
         	System.out.print("Saisir le code du materiel à chercher : ");
             String code = scanner.next(); 
             Optional<String> checkMaterialCode = Optional.of(code); 
             
             if (checkMaterialCode.isPresent()) {

             	if(checkType1.get().equals("1")) {
                 	gestionMaterielServiceFacade.chercherMateriel(TypeMateriel.LIVRE, checkMaterialCode.get());;
                 }
             	else if(checkType1.get().equals("2")) {
                 	gestionMaterielServiceFacade.chercherMateriel(TypeMateriel.CHAISE, checkMaterialCode.get());
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
		
	}
     
    private void ajoutMateriel() {
    	 
    	 System.out.print("1-pour livre \t 2-pour chaise : ");
         mat = scanner.next();
         Optional<String> checkType2 = Optional.of(mat); 
         
         if (checkType2.isPresent()) {

             	System.out.print("Saisir le code du nouveau materiel : ");
                 String code = scanner.next(); 
                 Optional<String> checkMaterialCode = Optional.of(code); 
                 System.out.print("Saisir le nombre de stock du materiel : ");
                 Integer  stock = scanner.nextInt(); 
                 Optional<Integer> checkMaterialStock = Optional.of(stock); 
                 
                 if (checkMaterialCode.isPresent() && checkMaterialStock.isPresent()) {

                 	if(checkType2.get().equals("1")) {
                     	gestionMaterielServiceFacade.ajouterNouveauMateriel(TypeMateriel.LIVRE, checkMaterialCode.get(), checkMaterialStock.get());
                     }
                 	else if(checkType2.get().equals("2")) {
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
		
	}
     
    private void supprimerMateriel() {
		
    	System.out.print("1-pour livre \t 2-pour chaise : ");
        mat = scanner.next();
        Optional<String> checkType3 = Optional.of(mat); 
        
        if (checkType3.isPresent()) {
        	
        	System.out.print("Saisir le code du materiel à supprimer : ");
            String code = scanner.next(); 
            Optional<String> checkMaterialCode = Optional.of(code); 
            
            if (checkMaterialCode.isPresent()) {

            	if(checkType3.get().equals("1")) {
                	gestionMaterielServiceFacade.supprimerMateriel(TypeMateriel.LIVRE, checkMaterialCode.get());
                }
            	else if(checkType3.get().equals("2")) {
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
    	
	}
    
    private void modifierMateriel() {
    	System.out.print("1-pour livre \t 2-pour chaise : ");
        mat = scanner.next();
        Optional<String> checkType4 = Optional.of(mat); 
        
        if (checkType4.isPresent()) {
        	
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
            		if(checkType4.get().equals("1")) {
                    	gestionMaterielServiceFacade.modifierMateriel(TypeMateriel.LIVRE, checkMaterialCode.get(), checkMaterialstock.get(), checkMaterialAncienCode.get());
                    }
                	else if(checkType4.get().equals("2")) {
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
    }
    
    private void materielIndisponible() {
		
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
    	
	}
    
    private void allouerMateriel(User user) {
    	
    	System.out.print("1-pour livre \t 2-pour chaise : ");
        mat = scanner.next();
        Optional<String> checkType6 = Optional.of(mat); 
        
        if (checkType6.isPresent()) {

            	System.out.print("Saisir le code du materiel à allouer : ");
                String code = scanner.next(); 
                Optional<String> checkMaterialCode = Optional.of(code); 
              
                if (checkMaterialCode.isPresent()) {

                	if(checkType6.get().equals("1")) {
                    	gestionMaterielServiceFacade.allouerMateriel(TypeMateriel.LIVRE, checkMaterialCode.get(), user);
                    }
                	else if(checkType6.get().equals("2")) {
                    	gestionMaterielServiceFacade.allouerMateriel(TypeMateriel.CHAISE, checkMaterialCode.get(), user);
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
        
	}
    
    private void rendreMateriel(User user) {
    	
    	System.out.print("1-pour livre \t 2-pour chaise : ");
        mat = scanner.next();
        Optional<String> checkType7 = Optional.of(mat); 
        
        if (checkType7.isPresent()) {

            	System.out.print("Saisir le code du materiel à rendre : ");
                String code = scanner.next(); 
                Optional<String> checkMaterialCode = Optional.of(code); 
              
                if (checkMaterialCode.isPresent()) {

                	if(checkType7.get().equals("1")) {
                    	gestionMaterielServiceFacade.rendreMateriel(TypeMateriel.LIVRE, checkMaterialCode.get(), user);
                    }
                	else if(checkType7.get().equals("2")) {
                    	gestionMaterielServiceFacade.rendreMateriel(TypeMateriel.CHAISE, checkMaterialCode.get(), user);
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
        
    }
    
    private void afficherMaterielAllouer(User user) {
		
    	gestionMaterielServiceFacade.listeMaterielAlloue(user);
    	
	}
    
    private void afficherListeMateriel() {
    	gestionMaterielServiceFacade.listeMateriel();
	}

}
