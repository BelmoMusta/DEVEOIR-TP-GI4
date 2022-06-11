package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.TypeMateriel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielServiceFacade;
import com.ensa.gi4.service.api.GestionUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Controller("controllerPricipal")
public class GestionMaterielController {

	@Autowired
	ApplicationPublisher publisher;

	@Autowired
	private GestionUserService gestionUserService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private GestionMaterielServiceFacade gestionMaterielServiceFacade;

	public void afficherMenu() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("-----------login--------------");
		System.out.println();
		System.out.println("enter your userName");
		String userName = scanner.next();
		System.out.println("enter your password");
		String password = scanner.next();
		System.out.println();
		// get the user withe userName
		User user = gestionUserService.getUser(userName);
		if (user != null) {
			// verify that the passwords mateches
			if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
				// get the roles of user and show him the coresponding menu
				List<Role> userRoles = gestionUserService.getUseRoles(user.getId());
				boolean isAdmin = false;
				for (Iterator iterator = userRoles.iterator(); iterator.hasNext();) {
					Role role = (Role) iterator.next();
					if (role.getName().equals("admin")) {
						isAdmin = true;
						break;
					} else {
						continue;
					}
				}
				System.out.println("what operation do you want to make?");
				System.out.println();
				boolean isLogged = true;
				if (isAdmin) {
					while (isLogged) {
						System.out.println("1-afficher la liste de tous les materiels");
						System.out.println("2-chercher un materiel");
						System.out.println("3-creer un materiel");
						System.out.println("4-modifier un materiel");
						System.out.println("5-supprimer un materiel");
						System.out.println("6-marquer un materiel comme non disponible");
						System.out.println("7-allouer un materiel");
						System.out.println("8-rendre un materiel");
						System.out.println("9-afficher la liste de mes materiels");
						System.out.println("10-afficher la liste des materiels alloue par chaque user");
						System.out.println("11-logout");
						System.out.println("0-si vous voulez sortir de l'application");
						int operation = scanner.nextInt();
						System.out.println();
						switch (operation) {
						case 1:
							afficherMateriel();
							System.out.println();
							break;
						case 2:
							search();
							System.out.println();
							break;
						case 3:
							createMaterial();
							break;
						case 4:
							modifierMateriel();
							System.out.println();
							break;
						case 5:
							removeMateriel();
							System.out.println();
							break;
						case 6:
							markeAsUnAvailable();
							System.out.println();
							break;
						case 7:
							alocateMateriel(user.getId());
							System.out.println();
							break;
						case 8:
							renderMateriel(user.getId());
							System.out.println();
							break;
						case 9:
							myMaterielList(userName);
							System.out.println();
							break;
						case 10:
							usersMateriels();
							System.out.println();
							break;
						case 11:
							isLogged = false;
							break;
						case 0:
							sortirDeLApplication();
							break;

						default:
							System.out.println("choix invalide");
							break;
						}
					}
				} else {
					while (isLogged) {
						System.out.println("1-afficher la liste de tous les materiels");
						System.out.println("2-chercher un materiel");
						System.out.println("3-allouer un materiel");
						System.out.println("4-rendre un materiel");
						System.out.println("5-afficher la liste de mes materiels");
						System.out.println("6-logout");
						System.out.println("0-si vous voulez sortir de l'application");
						System.out.println();
						int operation = scanner.nextInt();
						System.out.println();

						switch (operation) {
						case 1:
							afficherMateriel();
							System.out.println();
							break;
						case 2:
							search();
							System.out.println();
							break;
						case 3:
							alocateMateriel(user.getId());
							System.out.println();
							break;
						case 4:
							renderMateriel(user.getId());
							System.out.println();
							break;
						case 5:
							myMaterielList(userName);
							System.out.println();
							break;
						case 6:
							isLogged = false;
							break;
						case 7:
							sortirDeLApplication();
							break;
						default:
							System.out.println("choix invalid");
							break;
						}
					}
				}
			}
		} else {
			System.out.println("user name ou mot de pass invalid");
		}
	}

	// user operations
	private void sortirDeLApplication() {
		System.exit(0);
	}

	private void afficherMateriel() {
		System.out.println("1: pour afficher les livres 2: pour afficher les chaise");
		Scanner scanner = new Scanner(System.in);
		int type = scanner.nextInt();
		System.out.println();
		List<Materiel> materiels;
		switch (type) {
		case 1:
			materiels = gestionMaterielServiceFacade.afficherMateriel(TypeMateriel.LIVRE);
			for (Materiel materiel : materiels) {
				System.out.println(materiel.toString());
			}
			break;
		case 2:
			materiels = gestionMaterielServiceFacade.afficherMateriel(TypeMateriel.CHAISE);
			for (Materiel materiel : materiels) {
				System.out.println(materiel.toString());
			}
			break;
		default:
			System.out.println("choix invalid !!");
		}
	}

	private void search() {
		System.out.println("1: pour chercher les livres 2: pour chercher les chaise");
		Scanner scanner = new Scanner(System.in);
		int type = scanner.nextInt();
		System.out.println();
		System.out.println("entrez le nom du materiel");
		String nomMateriel = scanner.next();
		Materiel materiel;
		switch (type) {
		case 1:

			materiel = gestionMaterielServiceFacade.findOne(TypeMateriel.LIVRE, nomMateriel);
			if (materiel != null) {
				System.out.println(materiel.toString());
			} else {
				System.out.println("no book by this name");
			}

			break;
		case 2:
			materiel = gestionMaterielServiceFacade.findOne(TypeMateriel.CHAISE, nomMateriel);
			if (materiel != null) {
				System.out.println(materiel.toString());
			} else {
				System.out.println("no book by this name");
			}
			break;
		default:
			System.out.println("vous n'avez pas choisis le bon choix !!");
		}
	}

	private void createMaterial() {
		System.out.println("1: pour creer un livre 2: pour creer une chaise");
		Scanner scanner = new Scanner(System.in);
		int type = scanner.nextInt();
		System.out.println();
		switch (type) {
		case 1:
			Livre livre = new Livre();
			System.out.println("entrez le nom du livre");
			String nomString = scanner.next();
			System.out.println("entrez le code du livre");
			String code = scanner.next();
			System.out.println("entrez le nombre de stock du livre");
			int stock = scanner.nextInt();
			livre.setName(nomString);
			livre.setCode(code);
			livre.setStock(stock);
			livre.setAvailable(true);
			gestionMaterielServiceFacade.ajouterNouveauMateriel(TypeMateriel.LIVRE, livre);
			publisher.publish(new MyEvent<>(livre, EventType.ADD));
			break;
		case 2:
			Chaise chaise = new Chaise();
			System.out.println("entrez le nom du chaise");
			String nomChaise = scanner.next();
			System.out.println("entrez le code du chaise");
			String codeChaise = scanner.next();
			System.out.println("entrez le nombre de stock du chaise");
			int stockChaise = scanner.nextInt();
			chaise.setName(nomChaise);
			chaise.setCode(codeChaise);
			chaise.setStock(stockChaise);
			chaise.setAvailable(true);
			gestionMaterielServiceFacade.ajouterNouveauMateriel(TypeMateriel.CHAISE, chaise);
			publisher.publish(new MyEvent<>(chaise, EventType.ADD));
			break;
		default:
			System.out.println("vous n'avez pas choisis le bon choix !!");
		}
	}

	private void modifierMateriel() {
		System.out.println("1: pour modifier un livre 2: pour modifier une chaise");
		Scanner scanner = new Scanner(System.in);
		int type = scanner.nextInt();
		System.out.println();
		switch (type) {
		case 1:
			System.out.println("entrez le nom du livre que vous voulez modifier");
			String nomLivre = scanner.next();

			Materiel livre = gestionMaterielServiceFacade.findOne(TypeMateriel.LIVRE, nomLivre);
			if (livre != null) {

				System.out.println("entrez le nouveau nom du livre");
				String newName = scanner.next();
				System.out.println("entrez le nouveau code du livre");
				String newCode = scanner.next();
				System.out.println("entrez le nouveau nombre de stock du livre");
				int newStock = scanner.nextInt();
				livre.setName(newName);
				livre.setCode(newCode);
				livre.setStock(newStock);
				livre.setAvailable(true);
				gestionMaterielServiceFacade.modifierMateriel(TypeMateriel.LIVRE, livre.getId(), livre);
				publisher.publish(new MyEvent<>(livre, EventType.UPDATE));
			} else {
				System.out.println("no book by this name");
			}
			break;
		case 2:
			System.out.println("entrez le nom du chaise que vous voulez modifier");
			String nomChaise = scanner.next();
			Materiel chaise = gestionMaterielServiceFacade.findOne(TypeMateriel.CHAISE, nomChaise);
			if (chaise != null) {
				System.out.println("entrez le nouveau nom du livre");
				String newName = scanner.next();
				System.out.println("entrez le nouveau code du livre");
				String newCode = scanner.next();
				System.out.println("entrez le nouveau nombre de stock du livre");
				int newStock = scanner.nextInt();
				chaise.setName(newName);
				chaise.setCode(newCode);
				chaise.setStock(newStock);
				chaise.setAvailable(true);
				gestionMaterielServiceFacade.modifierMateriel(TypeMateriel.CHAISE, chaise.getId(), chaise);
				publisher.publish(new MyEvent<>(chaise, EventType.UPDATE));
			} else {
				System.out.println("no book by this name");
			}
			break;
		default:
			System.out.println("vous n'avez pas choisis le bon choix !!");
		}
	}

	private void removeMateriel() {
		System.out.println("1: pour supprimer un livre 2: pour supprimer une chaise");
		Scanner scanner = new Scanner(System.in);
		int type = scanner.nextInt();
		System.out.println();
		switch (type) {
		case 1:
			System.out.println("entrez le nom du livre que vous voulez supprimer");
			String nomLivre = scanner.next();
			Materiel livre = gestionMaterielServiceFacade.findOne(TypeMateriel.LIVRE, nomLivre);
			if ((livre != null)) {
				gestionMaterielServiceFacade.supprimerMateriel(TypeMateriel.LIVRE, livre.getId());
				publisher.publish(new MyEvent<>(livre, EventType.REMOVE));
			} else {
				System.out.println("no book by this name");
			}
			break;
		case 2:
			System.out.println("entrez le nom du chaise que vous voulez supprimer");
			String nomChaise = scanner.next();
			Materiel chaise = gestionMaterielServiceFacade.findOne(TypeMateriel.CHAISE, nomChaise);
			if (chaise != null) {
				gestionMaterielServiceFacade.supprimerMateriel(TypeMateriel.CHAISE, chaise.getId());
				publisher.publish(new MyEvent<>(chaise, EventType.REMOVE));
			} else {
				System.out.println("no chaise by this name");
			}
			break;
		default:
			System.out.println("vous n'avez pas choisis le bon choix !!");
		}
	}

	private void myMaterielList(String userName) {
		List<Materiel> materiels = gestionUserService.getMateriels(userName);
		for (Materiel materiel : materiels) {
			System.out.println(materiel.getName());
		}
	}

	private void alocateMateriel(int userId) {
		System.out.println("1: pour louer un livre 2: pour louer une chaise");
		Scanner scanner = new Scanner(System.in);
		int type = scanner.nextInt();
		System.out.println();
		switch (type) {
		case 1:
			System.out.println("entrez le nom du livre que vous voulez louer");
			String nomLivre = scanner.next();
			gestionMaterielServiceFacade.louerMateriel(TypeMateriel.LIVRE, nomLivre, userId);
			break;
		case 2:
			System.out.println("entrez le nom du chaise que vous voulez louer");
			String nomChaise = scanner.next();
			gestionMaterielServiceFacade.louerMateriel(TypeMateriel.CHAISE, nomChaise, userId);
			break;
		default:
			System.out.println("vous n'avez pas choisis le bon choix !!");
		}
	}

	private void renderMateriel(int userId) {
		System.out.println("1: pour rendre un livre 2: pour rendre une chaise");
		Scanner scanner = new Scanner(System.in);
		int type = scanner.nextInt();
		System.out.println();
		switch (type) {
		case 1:
			System.out.println("entrez le nom du livre que vous voulez rendre");
			String nomLivre = scanner.next();
			gestionMaterielServiceFacade.rendreMateriel(TypeMateriel.LIVRE, nomLivre, userId);
			break;
		case 2:
			System.out.println("entrez le nom du chaise que vous voulez rendre");
			String nomChaise = scanner.next();
			gestionMaterielServiceFacade.rendreMateriel(TypeMateriel.CHAISE, nomChaise, userId);
			break;
		default:
			System.out.println("vous n'avez pas choisis le bon choix !!");
		}
	}

	private void markeAsUnAvailable() {
		System.out.println("1: pour rendre un livre 2: pour rendre une chaise");
		Scanner scanner = new Scanner(System.in);
		int type = scanner.nextInt();
		System.out.println();
		switch (type) {
		case 1:
			System.out.println("entrez le nom du livre que vous voulez rendre non disponible");
			String nomLivre = scanner.next();
			gestionMaterielServiceFacade.markerNonDisponible(TypeMateriel.LIVRE, nomLivre);
			break;
		case 2:
			System.out.println("entrez le nom du chaise que vous voulez rendre non disponible");
			String nomChaise = scanner.next();
			gestionMaterielServiceFacade.markerNonDisponible(TypeMateriel.CHAISE, nomChaise);
			break;
		default:
			System.out.println("vous n'avez pas choisis le bon choix !!");
		}
	}

	private void usersMateriels() {
		List<User> users = gestionUserService.getUsers();
		for (User user : users) {
			System.out.println("user :" + user.getUserName() + " id:" + user.getId());
			myMaterielList(user.getUserName());
			System.out.println();
		}
	}
}
