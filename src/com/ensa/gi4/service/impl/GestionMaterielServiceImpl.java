package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;
import com.ensa.gi4.service.api.GestionMaterielService;

import resources.MessagesGestionMaterielServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
	
    @Autowired
    MaterielDao materielDao;
    
    @Autowired
    MessagesGestionMaterielServiceImpl message;

    @Override
    public void init() {
        System.out.println(message.initService);
    }

	@Override
	public Materiel chercherMaterielS(Long idMateriel) {
		
		Materiel materiel = materielDao.chercherMateriel(idMateriel);
		
		if(materiel == null) {
			
			System.out.println(message.nonExistenceMateriel);
	
		}else {
			
			System.out.println(message.ExistenceMateriel);
		
		}
		
		return materiel;
	}

	@Override
	public int allouerMaterielS(Long idMateriel, String etat, Long idUser) {
		
		Materiel materiel = materielDao.chercherMateriel(idMateriel);
		int check = 0;
		
		if(materiel.getEtat() == "Indisponible") {
			
			System.out.println(message.AllocationIndisponible);
		
		}else if(materiel.getEtat() == "Alloue") {
			
			System.out.println(message.AllocationAlloue);
		
		}else {
			
			check = materielDao.allouerMateriel(idMateriel, etat, idUser);
			
			if(check == 1) {
				
				System.out.println(message.AllocationReussi);
			
			}else {
				
				System.out.println(message.AllocationEchoue);
			}
		}
		
		
		return check;
	}

	@Override
	public int rendreMaterielS(Long idMateriel, String etat) {
		
		int check = materielDao.rendreMateriel(idMateriel, etat);
		
		return check;
	}

	@Override
	public List<Materiel> afficherMaterielS() {
		
		List<Materiel> listMateriel = materielDao.afficherMateriel();
		List<Livre> listLivres = null;
		List<Chaise> listChaises = null;
		
		for (Materiel item : listMateriel) {
			
			if(item.getCode() == "LI") {
				
				listLivres.add((Livre) item);
			}
			
			if(item.getCode() == "CH") {
				
				listChaises.add((Chaise) item);
			}
		}
		
		System.out.println("Votre liste de matériels contient : " + listLivres.size() + " livres.");
		System.out.println("Votre liste de matériels contient : " + listChaises.size() + " chaises.");
		return listMateriel;
	}

	@Override
	public List<Materiel> afficherMaterielUserS(Long idUser) {
		
		
		List<Materiel> listMaterielUser = materielDao.afficherMaterielUser(idUser);
		
		if(listMaterielUser.size() == 0) {
			
			System.out.println(message.AllocationPasEncore);
		
		}else {
			
			System.out.println(message.AllocationDejaFaite);
		}
		
		return listMaterielUser;
	}

	@Override
	public int ajouterNouveauMaterielS(Materiel materiel) {
		
		int check = materielDao.ajouterNouveauMateriel(materiel);
		
		if(check == 1) {
			
			System.out.println("Le matériel " + materiel.getName() + " est inséré avec succès");
		
		}else {
			
			System.out.println("Le processus d'insertion du matériel " + materiel.getName() + " a échoué");
		}
		
		return check;
	}

	@Override
	public int supprimerMaterielS(Long idMateriel) {
		
		int check = materielDao.supprimerMateriel(idMateriel);
		
		if(check == 1) {
			
			System.out.println(message.suppressionReussi);
			
		}else {
			
			System.out.println(message.suppressionEchoue);
		}
		
		return check;
	}

	@Override
	public int modifierMaterielS(Materiel materiel, Long idMateriel) {
		
		
		int check = materielDao.modifierMateriel(materiel, idMateriel);
		
		if(check == 1) {
			
			System.out.println(message.modificationReussi);
		
		}else {
			
			System.out.println(message.modificationEchoue);
		}
		
		return check;
	}

	@Override
	public int materielIndisponibleS(Long idMateriel, String etat) {
		
		int check = materielDao.materielIndisponible(idMateriel, etat);
		
		if(check == 1) {
			
			System.out.println(message.modificationReussi);
		
		}else {
			
			System.out.println(message.modificationEchoue);
		}
		
 		return check;
	}

	@Override
	public List<Materiel> afficherMaterielEveryUserS() {
		
		List<Materiel> listMaterielEveryUser = materielDao.afficherMaterielEveryUser();
		
		return listMaterielEveryUser;
	}

}
