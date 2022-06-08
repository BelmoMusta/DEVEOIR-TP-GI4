package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("gestionUser")
public class GestionUserServiceImpl implements GestionUserService{
	
	@Autowired
	UserDao userDao;
	

  @Autowired
  MaterielDao materielDao;
  
  @Autowired
  User user;
	@Override
	public void init() {
		
		System.out.println("initialisation du service user");
		
	}

	@Override
	public User connexion(String name, String password) {
		
		User user= userDao.findOneUser(name, password);
		
		
		if(user!= null){
			
			System.out.println("Vous êtes connecté(e)! 🎉");
			return user;
			
			
		}
		
		
		else {
			System.out.print("Votre nom ou mot de passe est erroné");
			return null;
		}
	
		
	}

	@Override
public Boolean  isAdmin(String name, String password) {
		String role = (String) (userDao.getRole(name));
		String admin= "admin";
		if(role.equals(admin)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	

	 //allocation 
	@Override
	public void allouerMateriel(String code, String duree) {
		if (materielDao.findWithCode(code) != null) {
				if (materielDao.isDispo(code)) {
				
					//allouer le materiel

					userDao.allouerMateriel(code, duree);
					materielDao.nonDispo(code);
					
					System.out.println("Votre allocation du "+materielDao.findWithCode(code).getName()+" a été effectuée");
					
					
				
			} else {
				System.out.println("Le materiel n'est pas disponible");
			}
		} else {
			System.out.println("Le Code entré n'existe pas");
		}
	}
	
	//rendreMateriel
	
	@Override
	public void rendreMateriel(int id) {

				if(userDao.rendreMateriel(id)) {
				System.out.println("Le materiel a été bien rendue");
			} else {
				System.out.println("Aucun materiel n'est alloué");
			}
		
	}

	@Override
	public void listeMaterielAlloue(String name) {

		if(!materielDao.listeAllocation(name).isEmpty()) {
			for(int i =0; i< materielDao.listeAllocation(name).size();i++)
		     System.out.println(materielDao.listeAllocation(name).get(i));
		
		}
	
	
		else {
			System.out.println("Vous n'avez pas alloué(e) aucun matériel");
		}
		
	}
	 

	public void supprimerMateriel(int id) {
		if(materielDao.supprimmerMateriel(id)) {
			System.out.println("La suppression a bien été effectuée");
		}else {
			System.out.println("une erreur a été survenu veuillez réessayer à nouveau!");
		}
		
	}

}

