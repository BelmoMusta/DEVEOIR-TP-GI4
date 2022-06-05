package com.ensa.gi4.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.TypeMateriel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.AllocationMaterielService;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionMaterielServiceFacade;



@Service
public class GestionMaterielServiceFacadeImpl implements GestionMaterielServiceFacade {

	@Autowired
	@Qualifier("livreService")
	GestionMaterielService livreService;
	@Autowired
	@Qualifier("chaiseService")
	GestionMaterielService chaiseService; 
	@Autowired
	AllocationMaterielService allocationMaterielService; 
	
	@Override
	public void ajouterNouveauMateriel(TypeMateriel type, String code, Integer stock) {
		
	 	final GestionMaterielService serviceActuel; 
		final Materiel materiel; 
		
		switch (type) {
		case LIVRE:
			materiel = new Livre(); 
			serviceActuel = livreService; 
			break;
		case CHAISE:
			materiel = new Chaise(); 
			serviceActuel = chaiseService;
			break;
		default:
			System.out.println("Le type " + type + " n'est pas reconnu");
			return;
		}
		
		materiel.setName(type.toString());
		materiel.setCode(code);
		materiel.setStock(stock);
		serviceActuel.ajouterNouveauMateriel(materiel);

	}



	@Override
	public void modifierMateriel(TypeMateriel type, String code, Integer stock, String ancienCode) {
		 final GestionMaterielService serviceActuel;

		 switch (type) {
			case LIVRE:
				serviceActuel = livreService; 
				break;
			case CHAISE:
				serviceActuel = chaiseService;
				break;
			default:
				System.out.println("Le type " + type + " n'est pas reconnu");
				return;
			}
		 
		 serviceActuel.modifierMateriel(code, stock, ancienCode);
	}

	@Override
	public void supprimerMateriel(TypeMateriel type, String code) {
		 final GestionMaterielService serviceActuel; 
		 
		 switch (type) {
			case LIVRE:
				serviceActuel = livreService; 
				break;
			case CHAISE:
				serviceActuel = chaiseService;
				break;
			default:
				System.out.println("Le type " + type + " n'est pas reconnu");
				return;
			}
		 
		 serviceActuel.supprimerMateriel(code);
		 
	}

	@Override
	public void listeMaterielAlloue(User user) {
		List<Materiel> materielList = allocationMaterielService.listeMaterielAlloue(user); 
		System.out.println("------------\tMateriels Alloués\t------------");
		if(!materielList.isEmpty() && Optional.of(materielList).isPresent()) {
			for (Materiel materiel : materielList) 
				System.out.println(materiel);
		}
		else {
			System.out.println("\tAucun élèment trouvé !");
		}
		
		System.out.println("----------------------------------------------------");

	}

	@Override
	public void materielIndisponible(TypeMateriel type, String code) {
		 final GestionMaterielService serviceActuel;
		 
		 switch (type) {
			case LIVRE:
				serviceActuel = livreService; 
				break;
			case CHAISE:
				serviceActuel = chaiseService;
				break;
			default:
				System.out.println("Le type " + type + " n'est pas reconnu");
				return;
			}
		 
		 serviceActuel.materielIndisponible(code);
		 
	}

	@Override
	public void chercherMateriel(TypeMateriel typeMateriel, String code) {
	
		Materiel materiel =  allocationMaterielService.chercherMateriel(typeMateriel, code);
		System.out.println("------------\tMateriel chercher\t------------");
		System.out.println(materiel);
		System.out.println("----------------------------------------------------");
	}



	@Override
	public void allouerMateriel(TypeMateriel typeMateriel, String code, User user) {
		
		allocationMaterielService.allouerMateriel(typeMateriel, code, user);
		
	}



	@Override
	public void rendreMateriel(TypeMateriel typeMateriel, String code, User user) {
		allocationMaterielService.rendreMaterielAlloue(typeMateriel, code, user);	
	}



	@Override
	public void listeMateriel() {
		List<Materiel> materielList =  allocationMaterielService.listeMateriel();
		
		System.out.println("------------\tListe des materiels\t------------");
		if(!Optional.of(materielList).get().isEmpty() && Optional.of(materielList).isPresent()) {
			for (Materiel materiel : materielList) 
				System.out.println(materiel);
		}
		else {
			System.out.println("\tAucun élèment trouvé !");
		}
		
		System.out.println("----------------------------------------------------");

	}



	@Override
	public void listeMaterielAlloueParUser(User user) {
		
		// affichage adequat
		switch (user.getRole()) {
		case ADMIN:
			allocationMaterielService.listeMaterielAlloueParUser();
			
			break;
		default:
			System.out.println("Opération non autorisé !");
			return;
		}
		
	}

}
