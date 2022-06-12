package com.ensa.gi4.controller;

import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Users;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionUsersService;
import com.ensa.gi4.service.impl.GestionMaterielServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

	@Autowired
    private GestionMaterielServiceImpl gestionLivreService;

    @Autowired
    @Qualifier("materielService")
    private GestionMaterielServiceImpl gestionMaterielService;

    @Autowired
    @Qualifier("usersService")
    private GestionUsersService gestionUsersService;
    
    @Autowired
    private Users userCo;
    
    @Value("${erreur.authentification}")
    private String erreurAuth;

    public void listerMateriel() {
        gestionMaterielService.listerMateriel();
    }

    public GestionMaterielController() {
    }

    public GestionMaterielController(GestionMaterielService livreService) {
        this.gestionLivreService = (GestionMaterielServiceImpl) livreService;
    }

    public void checkUsers() {
    
    	 System.out.println("Login:");
         Scanner scanner = new Scanner(System.in);
         String username = scanner.next();
         System.out.println("password:");
         String password = scanner.next();
     	 userCo = gestionUsersService.verifierUtilsateur(username, password);
     	while(userCo != null) {

        	if(userCo.getRole().equals("admin")) {
        	afficherMenuAdmin();
        	}else {
        	afficherMenuEmployee();
        	}
    
       } System.out.println(erreurAuth);
       System.out.println("si vous voulez quittez entrer 0, sinon entrer un autre nombre");
       String action = scanner.next();
       if(Integer.parseInt(action) == 0 ) sortirDeLApplication();
       
  
    }
    
  
    
    public void afficherMenuAdmin() {
        System.out.println("1- pour lister les matériaux, entrer 1");
        System.out.println("2- pour ajouter un nouveau matériel, entrer 2");
        System.out.println("3- pour supprimer un matériel, entrer 3");
        System.out.println("4- pour changer la disponibilité d'un matériel, entrer 4");
        System.out.println("5- pour allouer un matériel, entrer 5");
        System.out.println("6- pour afficher la liste des matériels alloués par chaque utilisateur, entrer 6");
        System.out.println("7- pour chercher un matériel , entrer 7");
        System.out.println("8- pour modifier le nom d'un matériel , entrer 8");
        System.out.println("9- pour rendre un matériel, entrer 9");
        System.out.println("10- pour voir la liste de tes matériels alloués, entrer 10");
        System.out.println("11- pour se deconnecter , entrer 11");

        System.out.println("0- pour sortir de l'application, entrer 0");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            sortirDeLApplication();
        } else if ("1".equals(next)) {
            listerMateriel();
        } else if ("2".equals(next)) {
        	// ajouter nouveau matériel
    		System.out.println("Pour ajouter un livre, entrer 1");
    		System.out.println("Pour ajouter une chaise , entrer 2");
    		String type = scanner.next();
    		System.out.println("Entrez son nom");
    		String nom = scanner.next();
    		System.out.println("Entrez son code");
    		String code = scanner.next();
    		//verifier si c'est un livre ou une chaise
    		if ("1".equals(type)) {
    			Livre l = new Livre();
    			l.setName(nom);
    			l.setCode(code);
    			gestionMaterielService.ajouterNouveauMateriel(l);
    		}else if ("2".equals(type)) {
    			Chaise c = new Chaise();
    			c.setName(nom);
    			c.setCode(code);
    			gestionMaterielService.ajouterNouveauMateriel(c);
    		}else {System.out.println("Type inéxistant");}
        	
        }else if("3".equals(next)) {
            System.out.println("code du materiel à supprimer");
            String asupp = scanner.next();
        	gestionMaterielService.suppMateriel(asupp);
        }else if("4".equals(next)) {
        	
            System.out.println("code du materiel à changer la disponibilite");
            String amarquer = scanner.next();
            System.out.println("1 pour marquer disponible, 0 pour marquer indisponible");
            String dispo = scanner.next();

            if(Integer.parseInt(dispo) == 1) {
            	if(gestionMaterielService.marquerDispo(true,amarquer) == 1) System.out.println("materiel marqué avec succées");
            	else System.out.println("Erreur lors du marquage");
            	}
            
            
            else if (Integer.parseInt(dispo) == 0) {
            	if(gestionMaterielService.marquerDispo(false,amarquer) == 1) System.out.println("materiel marqué avec succées"); 
            	else System.out.println("Erreur lors du marquage");
            	}
            else System.out.println("arguments invalide");
            


        }else if("5".equals(next)) {

        	 System.out.println("code du materiel à allouer");
             String aAllouer = scanner.next();

             if(gestionMaterielService.allouerMateriel(userCo.getId(), aAllouer) == 1 ) {
                 System.out.println("Materiel alloué avec succées");
             }else {System.out.println("Matériel introuvable ou il est déjà alloué");
             }
             
        }else if("6".equals(next)) {

        	
        	for(Users u : gestionUsersService.listUsers() ) {
        		
        	for(Materiel m : gestionMaterielService.toutMateriaux() ) {
   
        		if(u.getId() == m.getUserId()) System.out.println(" User  "+ m.getUserId() + " a alloué le matériel suivant : " + m.getCode());

        	}

    		}
           
        }else if("7".equals(next)) {
        	 System.out.println("code du materiel à chercher");
             String achercher = scanner.next();
             if(gestionMaterielService.findOne(achercher)) {
                 System.out.println("Materiel existant");
             }else {System.out.println("Materiel inexistant");
}
         }else if("8".equals(next)) {
        	 System.out.println("code du materiel à modifier");
             String amodifier = scanner.next();
             System.out.println("saisir le nouveau nom");
             String nvNom = scanner.next();
             if(gestionMaterielService.modifierMateriel(amodifier,nvNom) == 1)System.out.println("Nom modifié avec succées");
             else  System.out.println("Erreur lors de changement de nom");
             
           
         }else if("9".equals(next)) {
            
        	 System.out.println("code du materiel à rendre");
             String aRendre = scanner.next();

             if(gestionMaterielService.rendreMateriel(userCo.getId(), aRendre) == 1 ) {
                 System.out.println("Materiel rendu avec succées");
             }else {System.out.println("Opération echoué (Vérifier le code du materiél)");
             }
             
         }else if("10".equals(next)) {
        	 
        	 if(gestionMaterielService.materielAlloués(userCo.getId()).isEmpty())System.out.println("Vous n'avez alloués aucun matériel");
         	else {
         		for(Materiel m : gestionMaterielService.materielAlloués(userCo.getId())) {
         			System.out.println("id :"+ m.getId());
         			System.out.println("code :"+ m.getCode());
         			System.out.println("nom :"+ m.getName());

         		}
         	}
         }else if("11".equals(next)) {
        	 userCo = null ;
             checkUsers();
         }
        else {
            System.out.println("choix invalide");
        }
    }

    public void afficherMenuEmployee() {
        System.out.println("1- pour voir la liste des matériels, entrer 1");
        System.out.println("2- pour allouer un matériel, entrer 2");
        System.out.println("3- pour rendre un matériel, entrer 3");
        System.out.println("4- pour voir la liste de tes matériels alloués, entrer 4");
        System.out.println("5- pour chercher un matériel, entrer 5");
        System.out.println("6- pour se deconnecter , entrer 6");

        System.out.println("0- pour sortir de l'application, entrer 0");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            sortirDeLApplication();
        } else if ("1".equals(next)) {
            listerMateriel();
        } else if ("2".equals(next)) {
       	 System.out.println("code du materiel à allouer");
         String aAllouer = scanner.next();

         if(gestionMaterielService.allouerMateriel(userCo.getId(), aAllouer) == 1 ) {
             System.out.println("Materiel alloué avec succées");
         }else {System.out.println("Allocation echoué (Vérifier le code du materiél)");
         }
        }else if ("3".equals(next)) {
        	
        	 System.out.println("code du materiel à rendre");
             String aRendre = scanner.next();

             if(gestionMaterielService.rendreMateriel(userCo.getId(), aRendre) == 1 ) {
                 System.out.println("Materiel rendu avec succées");
             }else {System.out.println("Opération echoué (Vérifier le code du materiél)");
             }
        	
        	
        }else if ("4".equals(next)) {
        	
        	if(gestionMaterielService.materielAlloués(userCo.getId()).isEmpty())System.out.println("Vous n'avez alloués aucun matériel");
        	else {
        		for(Materiel m : gestionMaterielService.materielAlloués(userCo.getId())) {
        			System.out.println("id :"+ m.getId());
        			System.out.println("code :"+ m.getCode());
        			System.out.println("nom :"+ m.getName());

        		}
        	}
        }else if("5".equals(next)) {
        	 System.out.println("code du materiel à chercher");
             String achercher = scanner.next();
             if(gestionMaterielService.findOne(achercher)) {
                 System.out.println("Materiel existant");
             }else {System.out.println("Materiel inexistant");
             }
        }else if("6".equals(next)) {
            userCo = null ;
            checkUsers();
        }
        else {
            System.out.println("choix invalide");
        }
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
