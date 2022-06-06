package com.ensa.gi4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.PersonneDAO;
import com.ensa.gi4.modele.Personne;
import com.ensa.gi4.service.api.GestionPersonneService;

@Component
public class GestionPersonneServiceImpl implements GestionPersonneService {
	@Autowired
	MaterielDao materielDao;
	PersonneDAO personneDao;

	@Autowired
	public GestionPersonneServiceImpl(PersonneDAO personneDao) {
		this.personneDao = personneDao;
	}

	public Personne connecter(String nom, String pw) {
		if (personneDao.findPersonne(nom, pw) != null) {
			System.out.println("vous etes connecte : " + nom);
			return personneDao.findPersonne(nom, pw);
		} else {
			System.out.println("données eronnées");
			return null;
		}
	}

	/*
	 * @Override public void allouerMateriel(String code, String duree) {
	 * if(personneDao.allouerMateriel(code, duree)!=null) { System.out.
	 * println("Votre allocation a été bien enregistré, veuillez rendre le matériel avant "
	 * +duree); }else { System.out.println("impossible d'allouer ce materiel"); } }
	 */
	@Override
	public void allouerMateriel(String code, String duree) {
		if (materielDao.codeMatereielExiste(code) != null) {
			if (materielDao.estDisponible(code)) {
				if (materielDao.quantiteMateriel(code) > 0) {
					materielDao.diminuerQuantite(code);
					personneDao.allouerMateriel(code, duree);
					System.out.println("Votre allocation a bien été effectuée, merci de rendre la materiel avant "
							+ duree + "jours");

				} else {
					System.out.println("Ce materiel est épuise");
				}
			} else {
				System.out.println("Ce materiel n'est pas disponible");
			}
		} else {
			System.out.println("Ce Code n'existe pas");
		}
	}

	@Override
	public void rendreMateriel(String code) {
		if (materielDao.codeMatereielExiste(code) == null) {
			System.out.println("Ce Code n'existe pas");

		} else {
			if (personneDao.verifierExistanceAllocation(code)== true) {
				personneDao.rendreMateriel(code);
				System.out.println("Vous avez bien rendu le matériel");
			} else {
				System.out.println("Vous n'avez pas alloué ce matériel");
			}
		}
	}

}
