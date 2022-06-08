package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.PersonneDAO;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
	@Autowired
	PersonneDAO personneDao;
	@Autowired
	MaterielDao materielDao;

	@Override
	public void init() {
		System.out.println("inititialisation du service");
	}

	@Override
	public void listerMateriel() {
		if (!materielDao.findAll().isEmpty()) {

			System.out.println(materielDao.findAll());
		} else {
			System.out.println("Aucun mat�riel");
		}
	}

	@Override
	public void ajouterNouveauMateriel(Materiel materiel) {
		if (materielDao.ajouterMateriel(materiel)) {
			System.out.println("L'ajout du mat�riel " + materiel.getName() + " effectu�e avec succ�s !");
		} else {
			System.out.println("une erreur a �t� survenu lors du l'ajout du " + materiel.getName()
					+ "veuillez r�essayer � nouveau!");
		}
	}

	@Override
	public void findMateriel(Long id) {
		if (materielDao.findOne(id) != null) {
			System.out.println(materielDao.findOne(id));
		} else {
			System.out.println("le mat�riel n'existe pas");
		}

	}

	@Override
	public void supprimerMateriel(int id) {
		if (materielDao.supprimmerMateriel(id)) {
			System.out.println("La suppression a bien �t� effectu�e");
		} else {
			System.out.println("Cet id n'existe pas !");
		}

	}

	@Override
	public void modifierMateriel(int id, String nom, String code) {

		if (materielDao.modifierMateriel(id, nom, code)) {
			System.out.println("La modification a bien �t� effectu�e");
		} else {
			System.out.println("Cet id n'existe pas !");
		}

	}

	@Override
	public void marquerMaterielIndisponible(int id) {
		if (materielDao.idMaterielExiste(id)) {
			if (!materielDao.estDisponible(id)) {
				System.out.println("ce materiel est d�j� disponible !");
			} else {
				materielDao.marquerMaterielIndisponible(id);
				System.out.println("l'op�ration a �t� bien effectu�e, le mat�reil est indisponible");

			}
		} else {
			System.out.println("Cet id n'existe pas !");

		}
	}

	@Override
	public void afficherMaterielAllouerParUtilisateur() {
		materielDao.afficherMaterielAlloueParUtilisateur();
	}

	@Override
	public void rendreMateriel(int id) {

		if (materielDao.rendreMateriel(id)) {
			System.out.println("Vous avez bien rendu le mat�riel");
		} else {
			System.out.println("Vous n'avez pas allou� ce mat�riel");
		}

	}

	@Override
	public void allouerMateriel(String nom, String duree) {
		if (materielDao.allouerMateriel(nom, duree)) {
			System.out.println("Votre allocation a bien �t� effectu�e");
		} else {
			System.out.println("Ce materiel n'est pas disponible ou epuis�");
		}

	}

	@Override
	public boolean listerMaterielAlloue() {

		if (!materielDao.listerMaterielsAlloue().isEmpty()) {
			System.out.println(materielDao.listerMaterielsAlloue());
			return true;
		} else {
			System.out.println("Vous n'avez pas allou�(e) aucun mat�riel");
			return false;
		}

	}

}
