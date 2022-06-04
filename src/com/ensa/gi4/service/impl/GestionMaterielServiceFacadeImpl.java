package com.ensa.gi4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.TypeMateriel;
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
	public void listeMaterielAlloue() {
		// TODO Auto-generated method stub

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

}
