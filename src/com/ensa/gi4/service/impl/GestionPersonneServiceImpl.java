package com.ensa.gi4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.ensa.gi4.datatabase.api.PersonneDAO;
import com.ensa.gi4.modele.Personne;
import com.ensa.gi4.service.api.GestionPersonneService;
@Component
public class GestionPersonneServiceImpl implements GestionPersonneService {
	 
	 PersonneDAO personneDao;
	 @Autowired
	 public GestionPersonneServiceImpl(PersonneDAO personneDao) {
		 this.personneDao = personneDao;
	 }
public Personne connecter(String nom, String pw) {
	if(personneDao.findPersonne(nom, pw) != null) {
		System.out.println("vous etes connecte : "+ nom);
		return personneDao.findPersonne(nom, pw);
	}else {
		System.out.println("données eronnées");
		return null;
	}
}
}
