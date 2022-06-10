package com.ensa.gi4.controller;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {
	
	@Autowired 
	@Qualifier("materielService")
	private GestionMaterielService gestionMaterielService;
	

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	public Boolean findUser(User user) {
		return userService.findUser(user);
	}
	public void listerMateriel() {
		gestionMaterielService.listerMateriel();
	}
	public void listerMaterielAlloue() {
		gestionMaterielService.listerMaterielAlloue();
	}
	public Boolean findRole(User user, String role) {
		return userService.findRole(user, role);
	}
	
	public void RechercheMateriel(Materiel materiel) {
		gestionMaterielService.rechercheMateriel(materiel);
	}
	
	public void ajouterNouveauMateriel(Materiel materiel) {
		gestionMaterielService.ajouterNouveauMateriel(materiel);
	}
	public void supprimerMateriel(Materiel materiel) {
		gestionMaterielService.supprimerMateriel(materiel);
	}
	
	public void modifierMateriel(Materiel materiel,String nouveauCode) {
		gestionMaterielService.modifierMateriel(materiel,nouveauCode);
	}
	
	
	public void allouerMateriel(Materiel m,User user) {
		gestionMaterielService.allouerMateriel(m,user);
	}
	public void RendreMateriel(Materiel materiel,String username) {
		gestionMaterielService.RendreMateriel(materiel,username);
	}
	
	public void listerMaterielAlloueByName(String username) {
		gestionMaterielService.listerMaterielAlloueByName(username);
	}
	
	public User findUserInfo(String username,String password) {
		return userService.findUserInfo(username, password);
	}
	
	static private String username;
	static private String password;

//Authentification de l'utilisateur	
	public void login() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer votre nom :");
	    username = sc.nextLine();
		System.out.println("Entrer votre mot de passe :");
		password = sc.nextLine();
	    User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		Boolean result=findUser(user);
		if(result==true) {
			if(findRole(user,"ADMIN")) {
				while (true) {
			  System.out.println("Menu Admin :\n");
				afficherMenuAdmin();}
			}else if(findRole(user,"USER")) {
				while (true) {
			  System.out.println("Menu User :\n");
			   afficherMenuUser();}}
		}else {
			System.out.println("Login failed !!!!");
		}
	}
	
// Menu de l'utilisateur	
	 public void afficherMenuUser() {
		    System.out.println("0- pour se déconnecter, entrer 0");
	        System.out.println("1- pour lister le materiel, entrer 1");
	        System.out.println("2- pour allouer un materiel, entrer 5");
	        System.out.println("3- pour chercher un materiel, entrer 3");
	        System.out.println("4- pour lister les materiels alloues par lui meme, entrer 4");
	        System.out.println("5- pour rendre un materiel, entrer 5");
	        Scanner scanner = new Scanner(System.in);
	        String next = scanner.next();
	        if ("0".equals(next)) {
	            sortirDeLApplication();
	        } else if ("1".equals(next)) {
	           listerMateriel();
	        } else if ("2".equals(next)) {
	           
	        	 Scanner sc = new Scanner(System.in);
	        	 System.out.println("pour allouer un livre tapez 1 ");
	         	 System.out.println("pour allouer une chaise tapez 2 ");
	         	 String tap = sc.nextLine();
	         	User user = new User();
	         	user.setUsername(username);
	        	  if("1".equals(tap)) {
	        		 Materiel livre = new Livre();
		        	 System.out.println("donner le code du livre:");
		        	 String codeLivre = sc.nextLine();
		        	 livre.setCode(codeLivre);
		        	 allouerMateriel(livre,user);}
	        	  else if("2".equals(tap)){
	        		 Materiel livre = new Chaise();
		        	 System.out.println("donner le code de la chaise:");
		        	 String codeChaise = sc.nextLine();
		        	 livre.setCode(codeChaise);
		        	 allouerMateriel(livre,user);}
	        	}else if("3".equals(next)){
	         		 Scanner scann = new Scanner(System.in);
		        	 System.out.println("pour chercher un livre tapez 1 :");
		         	 System.out.println("pour chercher une chaise tapez 2 :");
		         	 String tap2 = scann.nextLine();
	         	     if("1".equals(tap2)) {
	         	    	Materiel livreRechercher = new Livre();
		         	    System.out.println("Donner le code du Livre a rendre");
		         	    String codLivre = scann.nextLine();
		         	    livreRechercher.setCode(codLivre);
		         	   RechercheMateriel(livreRechercher);}
	         	     else if("2".equals(tap2)) {
	         	    	Materiel chaiseRechercher = new Chaise();
		         	    System.out.println("Donner le code du Livre a rendre");
		         	    String codLivre = scann.nextLine();
		         	  chaiseRechercher.setCode(codLivre);
		         	   RechercheMateriel(chaiseRechercher);
	         	     }
	        	 }else if("4".equals(next)) { 
		         		listerMaterielAlloueByName(username);
		         	}
	        	else if ("5".equals(next)) {
	        	 Scanner scan = new Scanner(System.in);
	        	 System.out.println("pour rendre un livre tapez 1 ");
	         	 System.out.println("pour rendre une chaise tapez 2 ");
	         	 String tap1 = scan.nextLine();
	         	if("1".equals(tap1)) {
	         	    Materiel livreARendre = new Livre();
	         	    System.out.println("Donner le code du Livre à rendre");
	         	    String codLivre = scan.nextLine();
	         	   livreARendre.setCode(codLivre);
	         	   RendreMateriel(livreARendre,username);
	         	}else if("2".equals(tap1)) {
	         	   Materiel chaiseARendre = new Chaise();
	         	    System.out.println("Donner le code de la chaise à rendre :");
	         	    String codChaise = scan.nextLine();
	         	  chaiseARendre.setCode(codChaise);
	         	   RendreMateriel(chaiseARendre,username);}
	           }else {
	            System.out.println("choix invalide");
	        }
	    }
	 
	    private void sortirDeLApplication() {
	        login();
	    }
	    
//Menu d'administrateur	    
	    public void afficherMenuAdmin() {
	    	    System.out.println("0- pour se déconnecte, entrer 0");
	    	    System.out.println("1- pour lister le materiel, entrer 1");
		        System.out.println("2- pour ajouter un nouveau materiel, entrer 2");
		        System.out.println("3- pour modifier un materiel, entrer 3");
		        System.out.println("4- pour supprimer un materiel, entrer 4");
		        System.out.println("5- pour chercher un materiel, entrer 5");
		        System.out.println("6- pour allouer un materiel, entrer 6");
		        System.out.println("7- pour lister les materiels alloués, entrer 7");
		        System.out.println("8- pour rendre un materiel, entrer 8");
		        Scanner scanner = new Scanner(System.in);
		        String next = scanner.next();
		        if ("0".equals(next)) {
		            sortirDeLApplication();
		        } else if ("1".equals(next)) {
		           listerMateriel();
		        } else if("2".equals(next)) {
		        	 Scanner scanne = new Scanner(System.in);
		        	 System.out.println("pour ajouter un livre tapez 1  :");
		         	 System.out.println("pour ajouter une chaise tapez 2 :");
		         	 String tap1 = scanne.nextLine();
		         	 if("1".equals(tap1)) {
		         		Materiel livreAjoute = new Livre();
		         		System.out.println("Donner le nom du Livre a ajoute (Livre) : \n");
		         	    String nomLivreAjoute = scanne.nextLine();
		         	    System.out.println("Donner le code du Livre a ajoute");
		         	    String codLivreAjoute = scanne.nextLine();
		         	    livreAjoute.setName(nomLivreAjoute);
		         	    livreAjoute.setCode(codLivreAjoute);
		         	    ajouterNouveauMateriel(livreAjoute);
		         	 
		         	 }else if("2".equals(next)) {
		         		Materiel chaiseAjoute = new Chaise();
		         		 Scanner s = new Scanner(System.in);
		         		System.out.println("Donner le nom de la Chaise a ajoutée (Chaise) : ");
		         	    String nomChaiseAjoute = s.nextLine();
		         	    System.out.println("Donner le code de la Chaise a ajoute : ");
		         	    String codChaiseAjoute = s.nextLine();
		         	   chaiseAjoute.setName(nomChaiseAjoute);
		         	  chaiseAjoute.setCode(codChaiseAjoute);
		         	    ajouterNouveauMateriel(chaiseAjoute);
		         	 }	 
		        }
		    	else if("3".equals(next)) {
		    		//name obligatoire
		    		 Scanner sca = new Scanner(System.in);
		        	 System.out.println("pour modifier un livre tapez 1 :");
		         	 System.out.println("pour modifier une chaise tapez 2 :");
		         	 String tap4 = sca.nextLine();
		         	 if("1".equals(tap4)) {
		         		Materiel livreModifie = new Livre();
		         		 
			         	  System.out.println("Donner l'ancien code du Livre a modifier");
			         	 String nomLivreAncien = sca.nextLine();
			         	 livreModifie.setCode(nomLivreAncien);
			         	  
			         	  System.out.println("Donner le nouveau code  du Livre a modifier");
			         	 String nomLivreNouveau = sca.nextLine();
			         	  modifierMateriel(livreModifie,nomLivreNouveau);
		         	 }else if("2".equals(tap4)) {
		         		 
		         	 }
	         		
	         	} else if("4".equals(next)) {
	         		
	         		 Scanner scanne = new Scanner(System.in);
		        	 System.out.println("pour supprimer un livre tapez 1 :");
		         	 System.out.println("pour supprimer une chaise tapez 2 :");
		         	 String tap3 = scanne.nextLine();
		         	 if("1".equals(tap3)) {
		         		Materiel livreSupprime = new Livre();
		         	    System.out.println("Donner le code du Livre a ajoute");
		         	    String codLivreSupprime = scanne.nextLine();
		         	  livreSupprime.setCode(codLivreSupprime);
		         	   supprimerMateriel(livreSupprime);
		         	 }else if("2".equals(tap3)) {
		         		Materiel chaiseSupprime = new Chaise();
		         	    System.out.println("Donner le code de la chaise a ajoute");
		         	    String codChaiseSupprime = scanne.nextLine();
		         	   chaiseSupprime.setCode(codChaiseSupprime);
		         	   supprimerMateriel(chaiseSupprime);
		         	 }
	         		
	         	}
	         	else if("5".equals(next)){
	         		 Scanner scann = new Scanner(System.in);
		        	 System.out.println("pour chercher un livre tapez 1 :");
		         	 System.out.println("pour chercher une chaise tapez 2 :");
		         	 String tap2 = scann.nextLine();
	         	     if("1".equals(tap2)) {
	         	    	Materiel livreRechercher = new Livre();
		         	    System.out.println("Donner le code du Livre a rendre");
		         	    String codLivre = scann.nextLine();
		         	    livreRechercher.setCode(codLivre);
		         	   RechercheMateriel(livreRechercher);
	         	     }else if("2".equals(tap2)) {
	         	    	Materiel chaiseRechercher = new Chaise();
		         	    System.out.println("Donner le code du Livre a rendre");
		         	    String codLivre = scann.nextLine();
		         	  chaiseRechercher.setCode(codLivre);
		         	   RechercheMateriel(chaiseRechercher);
	         	     } 
	         	 }
		        
		        else if ("6".equals(next)) {
		           
		        	 Scanner sc = new Scanner(System.in);
		        	 System.out.println("pour allouer un livre tapez 1 ");
		         	 System.out.println("pour allouer une chaise tapez 2 ");
		         	 String tap = sc.nextLine();
		         	User user = new User();
		         	user.setUsername(username);
		        	if("1".equals(tap)) {
		        		 Materiel livre = new Livre();
			        	 System.out.println("donner le code du livre:");
			        	 String code = sc.nextLine();
			        	 livre.setCode(code);
			        	 allouerMateriel(livre,user);
		        	}else if("2".equals(tap)) {
		        		Materiel chaise = new Chaise();
			        	 System.out.println("donner le code de la chaise :");
			        	 String code = sc.nextLine();
			        	 chaise.setCode(code);
			        	 allouerMateriel(chaise,user);
		        	}
		        }
		         	else if("7".equals(next)) {
		         		listerMaterielAlloue();
		        	 
		         }else if ("8".equals(next)) {
		        	 Scanner scan = new Scanner(System.in);
		        	 System.out.println("pour rendre un livre tapez 1 ");
		         	 System.out.println("pour rendre une chaise tapez 2 ");
		         	 String tap1 = scan.nextLine();
		         	if("1".equals(tap1)) {
		         	    Materiel livreARendre = new Livre();
		         	    System.out.println("Donner le code du Livre a rendre");
		         	    String codLivre = scan.nextLine();
		         	   livreARendre.setCode(codLivre);
		         	   RendreMateriel(livreARendre,username);
		         	}else if("2".equals(tap1)) {
		         	   Materiel chaiseARendre = new Chaise();
		         	    System.out.println("Donner le code de la Chaise a rendre :");
		         	    String codChaise = scan.nextLine();
		         	  chaiseARendre.setCode(codChaise);
		         	   RendreMateriel(chaiseARendre,username);
		         	}
		         }
		        	else {
		            System.out.println("choix invalide");
		        }
	    	
	    }
}
