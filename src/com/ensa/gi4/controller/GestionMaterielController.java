package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Externalisation;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Personne;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionPersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller("controllerPricipal")
public class GestionMaterielController {
	String donnee1, donnee2, donnee3;
	Long num1, num2, num3;
	Scanner scanner = new Scanner(System.in);
	@Autowired
	Externalisation externalisation;

	@Autowired
	ApplicationPublisher publisher;
	@Autowired
	GestionPersonneService gestionPersonneService;
	@Autowired
	@Qualifier("materielService")
	GestionMaterielService gestionMaterielService;

	public void afficherMenu() {

		// if (authentification() != null) {
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

	}

	private void afficherMenuAdmin() {

		afficherMenuEmploye();

		System.out.println(externalisation.msgAjout);
		System.out.println(externalisation.msgSupprimer);
		System.out.println(externalisation.msgModifie);
		System.out.println(externalisation.msgDisponible);
		System.out.println(externalisation.msgAllouerUser);
		System.out.println(externalisation.msgCreerCompte);

		System.out.println("-----------------------------------------------------------------------------");
	}

	private void afficherMenuEmploye() {
		System.out.println("-------------------------------------------------------------");
		System.out.println(externalisation.msgListerMateriel);
		System.out.println(externalisation.msgChercher);
		System.out.println(externalisation.msgAllouer);
		System.out.println(externalisation.msgRendre);
		System.out.println(externalisation.msgAfficherAllouer);

	}

	private Long verificationEntre() {
		boolean verification = false;
		while (!verification) {
			donnee1 = scanner.next();

			try {
				num1 = Long.parseLong(donnee1);
				verification = true;
			} catch (Exception e) {
				System.out.println(externalisation.msgSaisirId);

			}
		}
		return num1;
	}

	private void listerMateriel() {
		gestionMaterielService.listerMateriel();
	}

	public Personne authentification() {
		System.out.println(externalisation.msgBonjour);
		System.out.println(externalisation.msgSaisirNom);

		donnee1 = scanner.next();
		System.out.println(externalisation.msgSaisirMotDePasse);
		donnee2 = scanner.next();
		return gestionPersonneService.connecter(donnee1, donnee2);
	}

	public void creerCompteEmploye() {
		System.out.println("---------------création du compte type employé(e)--------------");
		do {
			System.out.println(externalisation.msgSaisirNomUser);
			donnee1 = scanner.next();
			System.out.println(externalisation.msgSaisirMotDePasse);
			donnee2 = scanner.next();
		} while (!gestionPersonneService.creerCompte(donnee1, donnee2, "employe"));

	}

	private void creerCompteAdmin() {
		System.out.println(externalisation.msgCreationDeCompte);
		System.out.println("saisir le nom d'utilisateur ");
		donnee1 = scanner.next();
		System.out.println("saisir le mot de passe ");
		donnee2 = scanner.next();
		System.out.println(externalisation.msgEtreEmloye);
		System.out.println(externalisation.msgEtreAdmin);
		donnee3 = scanner.next();
		while (!donnee3.equals("1") && !donnee3.equals("2")) {
			System.out.println(externalisation.msgCreationCompteFailure);
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
		System.out.println(externalisation.msgAjouterLivre);
		System.out.println(externalisation.msgAjoutChaise);
		donnee1 = scanner.next();
		while (!donnee1.equals("1") && !donnee1.equals("2")) {
			System.out.println(externalisation.msgCreationCompteFailure);
			donnee1 = scanner.next();
		}
		if (donnee1.equals("1")) {
			System.out.println(externalisation.msgAjoutCodeLivre);
			donnee2 = scanner.next();
			Materiel materiel = new Livre();
			materiel.setCode(donnee2);
			materiel.setName(donnee1);
			gestionMaterielService.ajouterNouveauMateriel(materiel);
			publisher.publish(new MyEvent<>(materiel, EventType.ADD));

		}
		if (donnee1.equals("2")) {
			System.out.println(externalisation.msgAjoutCodeChaise);
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
		System.out.println(externalisation.msgSupprimerId);
		listerMateriel();
		num1 = verificationEntre();
		if (gestionMaterielService.supprimerMateriel(num1).equals("null")) {
			System.out.println(externalisation.msgIdNonExist);
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
		System.out.println(externalisation.msgModifierId);
		listerMateriel();
		num1 = verificationEntre();
		System.out.println(externalisation.msgModifierCode);
		donnee2 = scanner.next();
		if (gestionMaterielService.modifierMateriel(num1, donnee2).equals("null")) {
			System.out.println(externalisation.msgIdNonExist);
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
		System.out.println(externalisation.msgSaisirId);
		num1 = verificationEntre();
		gestionMaterielService.findMateriel(num1);

	}

	// allouer
	private void allouerMateriel() {
		do {
			System.out.println(externalisation.msgAllouerLivre);
			System.out.println(externalisation.msgAllouerChaise);
			donnee1 = scanner.next();
		} while (!donnee1.equals("1") && !donnee1.equals("2"));
		System.out.println(externalisation.msgDuree);
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
			System.out.println(externalisation.msgIdMaterielRendre);
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
		System.out.println(externalisation.msgMaterielDisponible);
		num1 = verificationEntre();
		gestionMaterielService.marquerMaterielIndisponible(num1);
	}

	// afficher mat"riel alloués par chaque utilisateur
	private void afficherMaterielAllouerParUtilisateur() {
		gestionMaterielService.afficherMaterielAllouerParUtilisateur();
	}

}
