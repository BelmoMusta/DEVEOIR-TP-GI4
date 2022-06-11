package com.ensa.gi4.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.TypeMateriel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionMaterielServiceFacade;
import com.ensa.gi4.service.api.GestionUserService;

@Service
public class GestionMaterielFacadeImpl implements GestionMaterielServiceFacade {

	@Autowired
	@Qualifier("gestionLivreService")
	private GestionMaterielService gestionLivreService;
	@Autowired
	@Qualifier("gestionChaiseService")
	private GestionMaterielService gestionChaiseService;
	@Autowired
	private GestionUserService gestionUserService;

	@Override
	public List<Materiel> afficherMateriel(TypeMateriel type) {
		GestionMaterielService serviceActuel = null;
		assignTheAppropriateService(serviceActuel, type);
		List<Materiel> materiels = serviceActuel.findAll();
		return materiels;
	}

	@Override
	public void ajouterNouveauMateriel(TypeMateriel type, Materiel materiel) {
		GestionMaterielService serviceActuel = null;
		assignTheAppropriateService(serviceActuel, type);
		serviceActuel.add(materiel);
	}

	@Override
	public void supprimerMateriel(TypeMateriel type, int id) {
		GestionMaterielService serviceActuel = null;
		assignTheAppropriateService(serviceActuel, type);
		serviceActuel.delete(id);
	}

	@Override
	public Materiel findOne(TypeMateriel type, String nom) {
		GestionMaterielService serviceActuel = null;
		assignTheAppropriateService(serviceActuel, type);
		Materiel materiel = serviceActuel.findOne(nom);
		return materiel;
	}

	@Override
	public void modifierMateriel(TypeMateriel type, int id, Materiel materiel) {
		GestionMaterielService serviceActuel = null;
		assignTheAppropriateService(serviceActuel, type);
		serviceActuel.update(id, materiel);
	}

	@Override
	public void louerMateriel(TypeMateriel type, String nomMateriel, int userId) {
		GestionMaterielService serviceActuel = null;
		assignTheAppropriateService(serviceActuel, type);
		serviceActuel.louerMateriel(nomMateriel, userId);
	}

	@Override
	public void rendreMateriel(TypeMateriel type, String nomMateriel, int userId) {
		GestionMaterielService serviceActuel = null;
		boolean hasMateriel = false;
		User user = gestionUserService.getUser(userId);
		List<Materiel> materiels = gestionUserService.getMateriels(user.getUserName());
		for (Materiel materiel : materiels) {
			if (materiel.getName().equals(nomMateriel)) {
				hasMateriel = true;
				break;
			}
		}
		assignTheAppropriateService(serviceActuel, type);
		if (hasMateriel) {
			serviceActuel.rendreMateriel(nomMateriel, userId);
		} else {
			System.out.println("vous pouvez pas rendre ce materiel");
		}
	}

	@Override
	public void markerNonDisponible(TypeMateriel type, String nomMateriel) {
		GestionMaterielService serviceActuel = null;
		assignTheAppropriateService(serviceActuel, type);
		serviceActuel.markerNonDisponible(nomMateriel);
	}

	private void assignTheAppropriateService(GestionMaterielService serviceActuel, TypeMateriel type) {
		switch (type) {
		case LIVRE:
			serviceActuel = gestionLivreService;
			break;
		case CHAISE:
			serviceActuel = gestionChaiseService;
			break;
		default:
			System.out.println("Le type " + type + " n'est pas reconnu");
			return;
		}
	}

}
