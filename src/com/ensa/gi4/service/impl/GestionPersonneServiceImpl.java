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
		System.out.println("vous etes connecte : "+ nom );
		return personneDao.findPersonne(nom, pw);
	    }else {
		System.out.println("données eronnées");
		return null;
	    }
        }
    public void creerCompte(String nom, String pw) {
	   if((nom != null )&&(pw != null)) {
		 if(personneDao.findPersonne(nom, pw) == null) {
			 personneDao.ajouterPersonne(nom,pw);
			 
		 }else {
			 System.out.println("ce compte déjà existe , ressayer de se conncter");
		 }
	 }
	 else {
		 System.out.println("essayer d'entrer des valeurs raisonables !");
	 }
 }
    
    @Override
    public Boolean  isAdmin(String nom, String pw) {
    		String role = (String) (personneDao.getRole(nom));
    		String admin= "admin";
    		if(role.equals(admin)) {
    			return true;
    		}
    		else {
    			return false;
    		}
    		
    	}
    @Override
	public void allouerMateriel(String nom, String duree) {
		if(personneDao.allouerMateriel(nom, duree)) { 
			System.out.println("Votre allocation a bien été effectuée, merci de rendre la materiel avant "
											+ duree + " jours");
		}else {
			System.out.println("Ce materiel n'est pas disponible ou epuisé");
		}
		
	}
	@Override
	public void rendreMateriel(String nom) {
		if(personneDao.rendreMateriel(nom)) {
			System.out.println("Vous avez bien rendu le matériel");
		} else {
			System.out.println("Vous n'avez pas alloué ce matériel");
		}
	}
}