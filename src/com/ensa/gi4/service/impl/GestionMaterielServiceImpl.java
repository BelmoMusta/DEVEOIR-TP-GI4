package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;
import com.ensa.gi4.service.api.GestionMaterielService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
	
    @Autowired
    MaterielDao materielDao;

    @Override
    public void init() {
        System.out.println("Initialisation du service de Gestion du matériel");
    }

	@Override
	public Materiel chercherMaterielS(Long idMateriel) {
		
		Materiel materiel = materielDao.chercherMateriel(idMateriel);
		
		if(materiel == null) {
			
			System.out.println("Le matériel que vous cherchez n'existe pas");
	
		}else {
			
			System.out.println("Le matériel que vous cherchez existe");
		
		}
		
		return materiel;
	}

	@Override
	public int allouerMaterielS(Long idMateriel, String etat, Long idUser) {
		
		Materiel materiel = materielDao.chercherMateriel(idMateriel);
		int check = 0;
		
		if(materiel.getEtat() == "Indisponible") {
			
			System.out.println("Le matériel que vous voulez allouer est actuellement indisponibe");
		
		}else if(materiel.getEtat() == "Alloue") {
			
			System.out.println("Le matériel que vous voulez allouer est en allocation actuellement, veuillez vérifier plus tard");
		
		}else {
			
			check = materielDao.allouerMateriel(idMateriel, etat, idUser);
			
			if(check == 1) {
				
				System.out.println("Le processus d'allocation a réussi");
			
			}else {
				
				System.out.println("Le processus d'allocation a échoué");
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
			
			System.out.println("Vous n'avez pas encore effectué une allocation");
		
		}else {
			
			System.out.println("Vous avez effectué une allocation");
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
			
			System.out.println("Le processus de suppression a réussi");
			
		}else {
			
			System.out.println("Le processus de suppression a échoué");
		}
		
		return check;
	}

	@Override
	public int modifierMaterielS(Materiel materiel, Long idMateriel) {
		
		
		int check = materielDao.modifierMateriel(materiel, idMateriel);
		
		if(check == 1) {
			
			System.out.println("Le processus de modification a réussi");
		
		}else {
			
			System.out.println("Le processus de modification a échoué");
		}
		
		return check;
	}

	@Override
	public int materielIndisponibleS(Long idMateriel, String etat) {
		
		int check = materielDao.materielIndisponible(idMateriel, etat);
		
		if(check == 1) {
			
			System.out.println("Le processus de modification a réussi");
		
		}else {
			
			System.out.println("Le processus de modification a échoué");
		}
		
 		return check;
	}

	@Override
	public List<Materiel> afficherMaterielEveryUserS() {
		
		List<Materiel> listMaterielEveryUser = materielDao.afficherMaterielEveryUser();
		
		return listMaterielEveryUser;
	}

//    @Override
//    public void listerMateriel() {
//        System.out.println(materielDao.findAll());
//    }
//
//    @Override
//    public void ajouterNouveauMateriel(Materiel materiel) {
//
//        System.out.println("L'ajout du matÃ©riel " + materiel.getName() + " effectuÃ© avec succÃ¨s !");
//    }
}
