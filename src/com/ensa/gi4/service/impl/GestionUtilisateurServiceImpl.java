package com.ensa.gi4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ensa.gi4.datatabase.api.UtilisateurDao;
import com.ensa.gi4.modele.Utilisateur;
import com.ensa.gi4.service.api.GestionUtilisateurService;

@Component
public class GestionUtilisateurServiceImpl implements GestionUtilisateurService {
	 
	 UtilisateurDao utilisateurDao;
	 @Autowired
	 public GestionUtilisateurServiceImpl(UtilisateurDao personneDao) {
		 this.utilisateurDao = personneDao;
	 }
public Utilisateur connexion(String nom, String password) {
	if(utilisateurDao.findUtilisateur(nom, password) != null) {
		return utilisateurDao.findUtilisateur(nom, password);
	}else {
		System.out.println("données eronnées");
		return null;
	}
}
}
