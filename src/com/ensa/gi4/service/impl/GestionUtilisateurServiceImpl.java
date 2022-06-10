package com.ensa.gi4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ensa.gi4.datatabase.api.UtilisateurDAO;
import com.ensa.gi4.modele.Utilisateur;
import com.ensa.gi4.service.api.GestionUtilisateurService;

@Component("utilisateurService")
public class GestionUtilisateurServiceImpl implements GestionUtilisateurService {

	@Autowired
	UtilisateurDAO utilisateurDao;
	
	@Override
	public Utilisateur loginS(Utilisateur utilisateur) {
		
		Utilisateur checkUtilisateur= utilisateurDao.login(utilisateur);
		
		return checkUtilisateur;
	}
	
	

}
