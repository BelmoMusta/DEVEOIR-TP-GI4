package com.ensa.gi4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.PersonneDAO;
import com.ensa.gi4.modele.Personne;
import com.ensa.gi4.service.api.GestionPersonneService;

@Service
public class GestionPersonneServiceImpl implements GestionPersonneService {
	@Autowired
	PersonneDAO personneDao;

	
	

	public Personne connecter(String nom, String pw) {
		System.out.println(nom + " " + pw);
		if (personneDao.findPersonne(nom, pw) != null) {
			System.out.println("vous êtes connecté(e) : soyez le bienvenu  " + nom);
			return personneDao.findPersonne(nom, pw);
		} else {
			System.out.println("données eronnées");
			return null;
		}
	}
	
	@Override
	public String determinerRole() {

		return personneDao.determinerRole();
	}

	@Override
	public boolean creerCompte(String name, String pw, String role) {
		if(personneDao.creerCompte(name, pw, role)) {
			System.out.println("le compte a été bien créé");
			return true;
		}else {
			System.out.println("Ce compte existe déjà");
			return false;
		}
		
	}

	

}
