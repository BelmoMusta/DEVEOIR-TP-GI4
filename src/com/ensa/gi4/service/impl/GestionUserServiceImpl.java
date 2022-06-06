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
			
			System.out.println("Vous êtes connecté(e)!");
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
				if (materielDao.quantiteMateriel(code) > 0) {
					materielDao.diminuerQuantite(code);
					userDao.allouerMateriel(code, duree);
					System.out.println("Votre allocation du "+materielDao.findWithCode(code).getName()+"a été effectuée");

				} else {
					System.out.println("Le stock de ce materiel est épuisé");
				}
			} else {
				System.out.println("Ce materiel n'est pas disponible pour allouer");
			}
		} else {
			System.out.println("Ce Code n'existe pas");
		}
	}


}

