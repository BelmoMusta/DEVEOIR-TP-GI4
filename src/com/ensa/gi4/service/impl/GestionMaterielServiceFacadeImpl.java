package com.ensa.gi4.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
	@Qualifier("allocationMaterielServiceImpl")
	AllocationMaterielService allocationMaterielService; 
	
	@Value("${string.gestionMaterielServiceFacadeImpl.typeMateriel}")
	private String typeMateriel; 
	
	@Value("${string.gestionMaterielServiceFacadeImpl.reconnu}")
	private String reconnu; 
	
	@Value("${string.gestionMaterielServiceFacadeImpl.listeMaterielAlloue}")
	private String listeMaterielAlloue; 
	
	@Value("${string.gestionMaterielServiceFacadeImpl.listeMaterielAlloueFailed}")
	private String listeMaterielAlloueFailed; 
	
	@Value("${string.gestionMaterielServiceFacadeImpl.chercherMateriel}")
	private String chercherMateriel;
	
	@Value("${string.gestionMaterielServiceFacadeImpl.chercherMaterielFailed}")
	private String chercherMaterielFailed; 
	
	@Value("${string.gestionMaterielServiceFacadeImpl.listeMateriel}")
	private String listeMateriel; 
	
	@Value("${string.gestionMaterielServiceFacadeImpl.listeMaterielFailed}")
	private String listeMaterielFailed; 
	
	@Value("${string.gestionMaterielServiceFacadeImpl.listeMaterielAlloueParUserFailed}")
	private String listeMaterielAlloueParUserFailed; 
	
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
			System.out.println(typeMateriel + " " + type + " " + reconnu);
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
				System.out.println(typeMateriel + " " + type + " " + reconnu);
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
				System.out.println(typeMateriel + " " + type + " " + reconnu);
				return;
			}
		 
		 serviceActuel.supprimerMateriel(code);
		 
	}

	@Override
	public void listeMaterielAlloue(User user) {
		Optional<List<Materiel>> checkMaterielOptional = allocationMaterielService.listeMaterielAlloue(user); 
		System.out.println("------------\t" + listeMaterielAlloue + "\t------------");
		if(checkMaterielOptional.isPresent()) {
			for (Materiel materiel :checkMaterielOptional.get()) 
				System.out.println(materiel);
		}
		else {
			System.out.println("\t" + listeMaterielAlloueFailed);
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
				System.out.println(typeMateriel + " " + type + " " + reconnu);
				return;
			}
		 
		 serviceActuel.materielIndisponible(code);
		 
	}

	@Override
	public void chercherMateriel(TypeMateriel typeMateriel, String code) {
	
		Optional<Materiel> checkMateriel =  allocationMaterielService.chercherMateriel(typeMateriel, code);
		System.out.println("------------\t" + chercherMateriel + "\t------------");
		if (checkMateriel.isPresent()) {
			System.out.println(checkMateriel.get());
		}else {
			System.out.println("\t"+chercherMaterielFailed);
		}
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
		Optional<List<Materiel>> checkMaterielList =  allocationMaterielService.listeMateriel();
		
		System.out.println("------------\t"+ listeMateriel + "\t------------");
		if(checkMaterielList.isPresent()) {
			for (Materiel materiel : checkMaterielList.get()) 
				System.out.println(materiel);
		}
		else {
			System.out.println("\t" + listeMaterielFailed);
		}
		System.out.println("----------------------------------------------------");

	}



	@Override
	public void listeMaterielAlloueParUser(User user) {
		
		switch (user.getRole()) {
		case ADMIN:
			allocationMaterielService.listeMaterielAlloueParUser();
	
			break;
		default:
			System.out.println(listeMaterielAlloueParUserFailed);
			return;
		}
		
	}

}
