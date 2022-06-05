package com.ensa.gi4.service.impl;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;

@Service("livreService")
public class GestionLivreServiceImpl implements GestionMaterielService, SmartInitializingSingleton {

	@Autowired
	MaterielDao materielDao; 
	
	@Override
	public void afterSingletonsInstantiated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajouterNouveauMateriel(Materiel materiel) {
		int test =  materielDao.ajouter(materiel); 
		
		if (test != 1) {
			System.out.println("----------------------------------------------------");
			System.out.println("Echec d'ajout du materiel !");
			System.out.println("----------------------------------------------------");
		}else {
			System.out.println("----------------------------------------------------");
			System.out.println("Ajout du materiel reussit !");
			System.out.println("----------------------------------------------------");
		}
	}

	@Override
	public void supprimerMateriel(String code) {
		int test =  materielDao.supprimerMateriel(code);
		
		if (test != 1) {
			System.out.println("----------------------------------------------------");
			System.out.println("Desolé le materiel ne peut pas être supprimer car il est déjà alloué");
			System.out.println("----------------------------------------------------");
		}else {
			System.out.println("----------------------------------------------------");
			System.out.println("Supression du materiel reussit !");
			System.out.println("----------------------------------------------------");
		}
	}


	@Override
	public void modifierMateriel(String code, Integer stock, String ancienCode) {
		int test =  materielDao.updateMateriel(code, stock, ancienCode); 
		if (test != 1) {
			System.out.println("----------------------------------------------------");
			System.out.println("Echec de modification des informations  du materiel !");
			System.out.println("----------------------------------------------------");
		}else {
			System.out.println("----------------------------------------------------");
			System.out.println("Modification du materiel reussit !");
			System.out.println("----------------------------------------------------");
		}
	}

	@Override
	public void materielIndisponible(String code) {
		int test =  materielDao.materielIndisponible(code); 
		
		if (test != 1) {
			System.out.println("----------------------------------------------------");
			System.out.println("Echec d'opéreation !");
			System.out.println("----------------------------------------------------");
		}else {
			System.out.println("----------------------------------------------------");
			System.out.println("Le materiel est rendu indiponible !");
			System.out.println("----------------------------------------------------");
		}
		
	}

 
}
