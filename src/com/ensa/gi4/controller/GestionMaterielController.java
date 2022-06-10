package com.ensa.gi4.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ensa.gi4.modele.TypeMateriel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.AuthentificationService;
import com.ensa.gi4.service.api.GestionMaterielServiceFacade;

@Component("controllerPricipal")
public class GestionMaterielController {

 
    @Autowired
    @Qualifier("authentificationServiceImpl")
    AuthentificationService authentificationService; 
    @Autowired
    GestionMaterielServiceFacade gestionMaterielServiceFacade; 
    
    @Value("${string.gestionMaterielController.admin.ajouterMateriel}")
    private String addMateriel;
    
    @Value("${string.gestionMaterielController.admin.supprimerMateriel}")
    private String supprimerMateriel;
    
    @Value("${string.gestionMaterielController.admin.modifierMateriel}")
    private String modifierMateriel;
    
    @Value("${string.gestionMaterielController.admin.marquerMaterielIndispo}")
    private String marquerMaterielIndispo;
    
    @Value("${string.gestionMaterielController.admin.listeMaterielParUser}")
    private String listeMaterielParUser;
    
    @Value("${string.gestionMaterielController.user.chercherMateriel}")
    private String chercherMateriel;
    
    @Value("${string.gestionMaterielController.user.allouerMateriel}")
    private String allouerMateriel;
    
    @Value("${string.gestionMaterielController.user.rendreMateriel}")
    private String rendreMateriel;
    
    @Value("${string.gestionMaterielController.user.listeMaterielAllouer}")
    private String listeMaterielAllouer;
    
    @Value("${string.gestionMaterielController.user.listeMateriel}")
    private String listeMateriel;
    
    @Value("${string.gestionMaterielController.user.quitterApp}")
    private String quitterApp;

    @Value("${string.gestionMaterielController.menu.welcome}")
    private String welcome; 
    
    @Value("${string.gestionMaterielController.menu.service}")
    private String service; 
    
    @Value("${string.gestionMaterielController.menu.username}")
    private String usernameString; 
    
    @Value("${string.gestionMaterielController.menu.password}")
    private String passwordString; 
    
    @Value("${string.gestionMaterielController.user.choixFailed}")
    private String choixFailed; 
    
    @Value("${string.gestionMaterielController.userFailed}")
    private String userFailed; 
    
    @Value("${string.gestionMaterielServiceFacadeImpl.typeMateriel}")
	private String typeMateriel; 
	
	@Value("${string.gestionMaterielServiceFacadeImpl.reconnu}")
	private String reconnu;
	
	@Value("${string.gestionMaterielController.codeMaterielInvalid}")
	private String codeMaterielInvalid;
	
	@Value("${string.gestionMaterielController.operation.chercher.codeMateriel}")
	private String codeMateriel;
	
	@Value("${string.gestionMaterielController.operation.ajout.codeMateriel}")
	private String nouveauCodeMateriel;
	
	@Value("${string.gestionMaterielController.operation.ajout.stockMateriel}")
	private String stockMateriel;
	
	@Value("${string.gestionMaterielController.operation.choixMateriel}")
	private String choixMateriel;
  
    
    Scanner scanner  = new Scanner(System.in);
    String mat;
    
    public Optional<User> authentification(List<String> userData) {
    	return authentificationService.login(userData); 
    }
    
    public List<String> getUserData(){
    	String username; 
    	String password; 
    	List<String> userData = new ArrayList<String>();  
    	// à gerer le signUp
    	
    	System.out.print(usernameString);
    	username = scanner.next(); 
    	System.out.print(passwordString);
    	password = scanner.next(); 
    	
    	if (Optional.of(username).isPresent() && Optional.of(password).isPresent()) {
    		userData.add(0,username); 
    		userData.add(1,password); 
    	}
    	
    	return userData; 
    		
    }

    public void afficherMenu(User user) {
    	
    	switch (user.getRole()) {
    	
		case ADMIN:
			// Menu Admin
			
			System.out.println("0- " + quitterApp);
			System.out.println("1- " + chercherMateriel);
			System.out.println("2- " + addMateriel);
			System.out.println("3- " + supprimerMateriel);
	        System.out.println("4- " + modifierMateriel);
	        System.out.println("5- " + marquerMaterielIndispo); 
	        System.out.println("6- " + allouerMateriel); 
	        System.out.println("7- " + rendreMateriel); 
	        System.out.println("8- " + listeMaterielAllouer);
	        System.out.println("9- " + listeMaterielParUser);
	        System.out.println("10- " + listeMateriel);
	        
	        
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
		        	System.out.println(choixFailed);
		        	break;
		        }  
			
			break;
		case USER : 
			// Menu User
			
			System.out.println("0- " + quitterApp);
			System.out.println("1- " + chercherMateriel);
	        System.out.println("2- " + allouerMateriel); 
	        System.out.println("3- " + rendreMateriel); 
	        System.out.println("4- " + listeMaterielAllouer);
	        System.out.println("5- " + listeMateriel);
	     
	        
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
		        	System.out.println(choixFailed);
		        	break;
		        }  
	    	
			
			break;
		default:
			System.out.println(userFailed);
			break;
		}
       
    }
    public void greeting() {
    	System.out.println("******************************************************************************************");
    	System.out.println("\t" + welcome);
    	System.out.println("\t" + service);
    	System.out.println("******************************************************************************************");
	}

    private void sortirDeLApplication() {
        System.exit(0);
    }
    
    private void chercherMateriel() {

    	 
    	 System.out.print(choixMateriel);
         mat = scanner.next();
         Optional<String> checkType1 = Optional.of(mat); 
         
         if (checkType1.isPresent()) {
         	
         	System.out.print(codeMateriel);
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
 					System.out.println(typeMateriel + mat + reconnu);
 				}
				}
             else {
					System.out.println(codeMaterielInvalid);
				}
			}else {
				System.out.println(choixFailed);
			}
		
	}
     
    private void ajoutMateriel() {
    	 
    	 System.out.print(choixMateriel);
         mat = scanner.next();
         Optional<String> checkType2 = Optional.of(mat); 
         
         if (checkType2.isPresent()) {

             	System.out.print(codeMateriel);
                 String code = scanner.next(); 
                 Optional<String> checkMaterialCode = Optional.of(code); 
                 System.out.print(stockMateriel);
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
     					System.out.println(typeMateriel + mat + reconnu);
     				}
					}
                 else {
						System.out.println(codeMaterielInvalid);
					}
                 
			}else {
				System.out.println(choixFailed);
			}
		
	}
     
    private void supprimerMateriel() {
		
    	System.out.print(choixMateriel);
        mat = scanner.next();
        Optional<String> checkType3 = Optional.of(mat); 
        
        if (checkType3.isPresent()) {
        	
        	System.out.print(codeMateriel);
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
					System.out.println(typeMateriel + mat + reconnu);
				}
			}
            else {
				System.out.println(codeMaterielInvalid);
			}
		}else {
			System.out.println(choixFailed);
		}
    	
	}
    
    private void modifierMateriel() {
    	System.out.print(choixMateriel);
        mat = scanner.next();
        Optional<String> checkType4 = Optional.of(mat); 
        
        if (checkType4.isPresent()) {
        	
        	System.out.print(codeMateriel);
            String ancienCode = scanner.next(); 
            Optional<String> checkMaterialAncienCode = Optional.of(ancienCode); 
            
            System.out.print(nouveauCodeMateriel);
            String code = scanner.next(); 
            Optional<String> checkMaterialCode = Optional.of(code); 
            
            System.out.print(stockMateriel);
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
    					System.out.println(typeMateriel + mat + reconnu);
    				}
				}

			}
            else {
				System.out.println(codeMaterielInvalid);
			}
		}else {
			System.out.println(choixFailed);
		}
    }
    
    private void materielIndisponible() {
		
    	System.out.print(choixMateriel);
        mat = scanner.next();
        Optional<String> checkType5 = Optional.of(mat); 
        
        if (checkType5.isPresent()) {
        	
        	System.out.print(codeMateriel);
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
					System.out.println(typeMateriel + mat + reconnu);
				}
			}
            else {
				System.out.println(codeMaterielInvalid);
			}
		}else {
			System.out.println(choixFailed);
		}
    	
	}
    
    private void allouerMateriel(User user) {
    	
    	System.out.print(choixMateriel);
        mat = scanner.next();
        Optional<String> checkType6 = Optional.of(mat); 
        
        if (checkType6.isPresent()) {

            	System.out.print(codeMateriel);
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
    					System.out.println(typeMateriel + mat + reconnu);
    				}
				}
                else {
					System.out.println(codeMaterielInvalid);
				}
                
		}else {
			System.out.println(choixFailed);
		}
        
	}
    
    private void rendreMateriel(User user) {
    	
    	System.out.print(choixMateriel);
        mat = scanner.next();
        Optional<String> checkType7 = Optional.of(mat); 
        
        if (checkType7.isPresent()) {

            	System.out.print(codeMateriel);
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
    					System.out.println(typeMateriel + mat + reconnu);
    				}
				}
                else {
					System.out.println(codeMaterielInvalid);
				}
                
		}else {
			System.out.println(choixFailed);
		}
        
    }
    
    private void afficherMaterielAllouer(User user) {
		
    	gestionMaterielServiceFacade.listeMaterielAlloue(user);
    	
	}
    
    private void afficherListeMateriel() {
    	gestionMaterielServiceFacade.listeMateriel();
	}

}
