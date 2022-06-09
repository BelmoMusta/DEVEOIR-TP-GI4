package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Personne;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionPersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import java.util.Scanner;

@Controller("controllerPricipal")
public class GestionMaterielController {
	String donnee1, donnee2, donnee3;
	Long num1, num2, num3;
	Scanner scanner = new Scanner(System.in);

	@Autowired
	ApplicationPublisher publisher;
	@Autowired
	GestionPersonneService gestionPersonneService;
	@Autowired
	@Qualifier("materielService")
	GestionMaterielService gestionMaterielService;

	public void afficherMenu() {

		//if (authentification() != null) {
			if (gestionPersonneService.determinerRole().equals("admin")) {

				// ************************ADMIN***************************

				while (true) {

					afficherMenuAdmin();

					String choix = scanner.next();

					if (choix.equals("1")) {
						listerMateriel();
					} else if (choix.equals("2")) {
						chercherMateriel();
					} else if (choix.equals("3")) {
						allouerMateriel();
					} else if (choix.equals("4")) {
						rendreMateriel();

					} else if (choix.equals("5")) {
						listerMaterielAlloue();
					} else if (choix.equals("6")) {
						ajouterMateriel();

					} else if (choix.equals("7")) {
						supprimerMateriel();

					} else if (choix.equals("8")) {
						modifierMateriel();

					} else if (choix.equals("9")) {
						marquerMaterielIndisponible();

					} else if (choix.equals("10")) {
						afficherMaterielAllouerParUtilisateur();

					} else if (choix.equals("11")) {
						creerCompteAdmin();

					}

					else {
						System.out.println("choix invalid");
					}

				}

			} else {

				// ******************************employé**********************

				while (true) {
					afficherMenuEmploye();
					String choix = scanner.next();
					if (choix.equals("1")) {
						listerMateriel();
					} else if (choix.equals("2")) {

						chercherMateriel();
					} else if (choix.equals("3")) {
						allouerMateriel();
					} else if (choix.equals("4")) {
						rendreMateriel();

					} else if (choix.equals("5")) {
						listerMaterielAlloue();
					} else {
						System.out.println("choix invalid");
					}

				}

			}

	/*	} else {
			System.out.println(" saisir 0 pour sortir de l'application");
			System.out.println(" saisir 1 pour créer un compte ");
			System.out.println(" saisir n'import quel caractère pour réesayer à nouveau");

			String choix = scanner.next();
			if (choix.equals("0")) {
				sortirDeLApplication();
			} else if (choix.equals("1")) {
				creerCompteEmploye();
				afficherMenu();
			} else {
				afficherMenu();
			}
		}*/

	}

	private void afficherMenuAdmin() {

		afficherMenuEmploye();

		System.out.println("pour --  ajouter   -- un matériel  saisir        ----------------------> 6 ");
		System.out.println("pour --  supprimer -- un matériel  saisir        ----------------------> 7 ");
		System.out.println("pour --  modifier  -- un matériel  saisir        ----------------------> 8 ");
		System.out.println("pour --  marquer   -- votre matériel indisponible saisir --------------> 9 ");
		System.out.println("pour --  afficher  -- les matériels alloués par les utilisateurs saisir> 10 ");
		System.out.println("pour --  créer     -- un compte utilisateur saisir --------------------> 11 ");

		System.out.println("-----------------------------------------------------------------------------");
	}

	private void afficherMenuEmploye() {
		System.out.println("-------------------------------------------------------------");
		System.out.println("pour --  lister  ---- le materiele saisir        ----------------------> 1 ");
		System.out.println("pour --  chercher  -- un materiele saisir        ----------------------> 2 ");
		System.out.println("pour --  allouer  --- un materiel saisir         ----------------------> 3 ");
		System.out.println("pour --  rendre  ---- un matereil saisir         ----------------------> 4 ");
		System.out.println("pour --  afficher  -- le matériel alloués saisir ----------------------> 5 ");

	}

	private Long verificationEntre() {
		boolean verification = false;
		while (!verification) {
			donnee1 = scanner.next();

			try {
				num1 = Long.parseLong(donnee1);
				verification = true;
			} catch (Exception e) {
				System.out.println("Veuillez  saisir un numéro id convenable  : ");

			}
		}
		return num1;
	}

	private void listerMateriel() {
		gestionMaterielService.listerMateriel();
	}

	public Personne authentification() {
		System.out.println("----------------bonjour----------------");
		System.out.println(" saisir votre nom ");

		donnee1 = scanner.next();
		System.out.println(" saisir votre mot de passe ");
		donnee2 = scanner.next();
		return gestionPersonneService.connecter(donnee1, donnee2);
	}

	public void creerCompteEmploye() {
		System.out.println("---------------création du compte type employé(e)--------------");
		do {
			System.out.println("saisir votre nom d'utilisateur ");
			donnee1 = scanner.next();
			System.out.println("saisir votre mot de passe ");
			donnee2 = scanner.next();
		} while (!gestionPersonneService.creerCompte(donnee1, donnee2, "employe"));

	}

	private void creerCompteAdmin() {
		System.out.println("---------------création du compte--------------");
		System.out.println("saisir le nom d'utilisateur ");
		donnee1 = scanner.next();
		System.out.println("saisir le mot de passe ");
		donnee2 = scanner.next();
		System.out.println("pour être un employé saisir 1 ");
		System.out.println("pour être un admin saisir 2 ");
		donnee3 = scanner.next();
		while (!donnee3.equals("1") && !donnee3.equals("2")) {
			System.out.println("choix invalid, veuillez saisir 1 pour être employé, ou 2 pour admin ");
			donnee3 = scanner.next();
		}
		if (donnee3.equals("1")) {
			donnee3 = "employe";
		} else if (donnee3.equals("2")) {
			donnee3 = "admin";
		}
		gestionPersonneService.creerCompte(donnee1, donnee2, donnee3);
	}

	// ajouter
	private void ajouterMateriel() {
		System.out.println("pour ajouter un livre saisir 1 ");
		System.out.println("pour ajouter une chaise saisir 2 ");
		donnee1 = scanner.next();
		while (!donnee1.equals("1") && !donnee1.equals("2")) {
			System.out.println("choix invalid veuillez saisir 1 pour livre ou bien 2 pour chaise");
			donnee1 = scanner.next();
		}
		if (donnee1.equals("1")) {
			System.out.println("saisir le code du livre");
			donnee2 = scanner.next();
			Materiel materiel = new Livre();
			materiel.setCode(donnee2);
			materiel.setName(donnee1);
			gestionMaterielService.ajouterNouveauMateriel(materiel);
			publisher.publish(new MyEvent<>(materiel, EventType.ADD));

		}
		if (donnee1.equals("2")) {
			System.out.println("saisir le code du chaise");
			donnee2 = scanner.next();
			Materiel materiel = new Chaise();
			materiel.setCode(donnee2);
			materiel.setName(donnee1);
			gestionMaterielService.ajouterNouveauMateriel(materiel);
			publisher.publish(new MyEvent<>(materiel, EventType.ADD));

		}
	}

	// supprimer
	private void supprimerMateriel() {
		System.out.println("Veuillez saisir l'id du matériel à supprimer ");
		listerMateriel();
		num1 = verificationEntre();
		if (gestionMaterielService.supprimerMateriel(num1).equals("null")) {
			System.out.println("cet id n'existe pas");
		} else if (gestionMaterielService.supprimerMateriel(num1).equals("Livre")) {
			Materiel materiel = new Livre();
			materiel.setCode(donnee2);
			materiel.setId(num1);
			materiel.setName("Livre");
			publisher.publish(new MyEvent<>(materiel, EventType.REMOVE));
		} else {
			Materiel materiel = new Chaise();
			materiel.setCode(donnee2);
			materiel.setId(num1);
			materiel.setName("Chaise");
			publisher.publish(new MyEvent<>(materiel, EventType.REMOVE));
		}

	}

	// modifier
	private void modifierMateriel() {
		System.out.println("Veuillez saisir l'id du matériel à modifier ");
		listerMateriel();
		num1 = verificationEntre();
		System.out.println("Veuillez saisir le nouveau code du matériel ");
		donnee2 = scanner.next();
		if (gestionMaterielService.modifierMateriel(num1, donnee2).equals("null")) {
			System.out.println("cet id n'existe pas");
		} else if (gestionMaterielService.modifierMateriel(num1, donnee2).equals("Livre")) {
			Materiel materiel = new Livre();
			materiel.setCode(donnee2);
			materiel.setId(num1);
			materiel.setName("Livre");
			publisher.publish(new MyEvent<>(materiel, EventType.UPDATE));
		} else {
			Materiel materiel = new Chaise();
			materiel.setCode(donnee2);
			materiel.setId(num1);
			materiel.setName("Chaise");
			publisher.publish(new MyEvent<>(materiel, EventType.UPDATE));
		}
	}

	// chercher
	private void chercherMateriel() {
		System.out.println(" saisir id : ");
		num1 = verificationEntre();
		gestionMaterielService.findMateriel(num1);

	}

	// allouer
	private void allouerMateriel() {
		do {
			System.out.println("Pour allouer un livre saisir 1 ");
			System.out.println("Pour allouer une chaise saisir 2 ");
			donnee1 = scanner.next();
		} while (!donnee1.equals("1") && !donnee1.equals("2"));
		System.out.println("saisir la duree d'allocation ");
		donnee2 = scanner.next();
		if (donnee1.equals("1")) {
			gestionMaterielService.allouerMateriel("Livre", donnee2);
		} else {
			gestionMaterielService.allouerMateriel("Chaise", donnee2);
		}

	}

	// rendre matériel
	private void rendreMateriel() {
		if (!gestionMaterielService.listerMaterielAlloue()) {

		} else {
			System.out.println("Saisir l'id du matériel à rendre");
			num1 = verificationEntre();
			gestionMaterielService.rendreMateriel(num1);
		}
	}

	// lister matériel par l'utilisateur lui même
	private void listerMaterielAlloue() {
		gestionMaterielService.listerMaterielAlloue();
	}

	// marquer matériel indisponible
	private void marquerMaterielIndisponible() {
		System.out.println("Veuillez saisir l'id du matériel à marquer indisponible");
		num1 = verificationEntre();
		gestionMaterielService.marquerMaterielIndisponible(num1);
	}

	// afficher mat"riel alloués par chaque utilisateur
	private void afficherMaterielAllouerParUtilisateur() {
		gestionMaterielService.afficherMaterielAllouerParUtilisateur();
	}

	// sortir de l'application
	/*public void sortirDeLApplication() {
		System.out.print("Merci pour votre visite");
		System.exit(0);
	}*/

}
