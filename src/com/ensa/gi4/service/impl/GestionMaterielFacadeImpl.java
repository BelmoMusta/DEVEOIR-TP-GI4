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
		final GestionMaterielService serviceActuel;
		switch (type) {
		case LIVRE:
			serviceActuel = gestionLivreService;
			break;
		case CHAISE:
			serviceActuel = gestionChaiseService;
			break;
		default:
			System.out.println("Le type " + type + " n'est pas reconnu");
			return null;
		}
		List<Materiel> materiels = serviceActuel.findAll();
		return materiels;
	}

	@Override
	public void ajouterNouveauMateriel(TypeMateriel type, Materiel materiel) {
		final GestionMaterielService serviceActuel;
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
		serviceActuel.add(materiel);
	}

	@Override
	public void supprimerMateriel(TypeMateriel type, int id) {
		final GestionMaterielService serviceActuel;
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
		serviceActuel.delete(id);
	}

	@Override
	public Materiel findOne(TypeMateriel type, String nom) {
		final GestionMaterielService serviceActuel;
		switch (type) {
		case LIVRE:
			serviceActuel = gestionLivreService;
			break;
		case CHAISE:
			serviceActuel = gestionChaiseService;
			break;
		default:
			System.out.println("Le type " + type + " n'est pas reconnu");
			return null;
		}
		Materiel materiel = serviceActuel.findOne(nom);
		return materiel;
	}

	@Override
	public void modifierMateriel(TypeMateriel type, int id, Materiel materiel) {
		final GestionMaterielService serviceActuel;
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
		serviceActuel.update(id, materiel);
	}

	@Override
	public void louerMateriel(TypeMateriel type, String nomMateriel, int userId) {
		final GestionMaterielService serviceActuel;
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
		serviceActuel.louerMateriel(nomMateriel, userId);
	}

	@Override
	public void rendreMateriel(TypeMateriel type, String nomMateriel, int userId) {
		final GestionMaterielService serviceActuel;
		boolean hasMateriel = false;
		User user = gestionUserService.getUser(userId);
		List<Materiel> materiels = gestionUserService.getMateriels(user.getUserName());
		for (Materiel materiel : materiels) {
			if (materiel.getName().equals(nomMateriel)) {
				hasMateriel = true;
				break;
			}
		}
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
		if (hasMateriel) {
			serviceActuel.rendreMateriel(nomMateriel, userId);
		}else {
			System.out.println("vous pouvez pas rendre ce materiel");
		}
	}

	@Override
	public void markerNonDisponible(TypeMateriel type, String nomMateriel) {
		final GestionMaterielService serviceActuel;
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
		serviceActuel.markerNonDisponible(nomMateriel);
	}

}
