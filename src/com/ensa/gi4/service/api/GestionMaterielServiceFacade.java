package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.TypeMateriel;
import com.ensa.gi4.modele.User;

public interface GestionMaterielServiceFacade {
	
	void ajouterNouveauMateriel(TypeMateriel type, String code, Integer stock);
	void modifierMateriel(TypeMateriel typeMateriel, String code, Integer stock, String ancienCode); 
	void supprimerMateriel(TypeMateriel typeMateriel, String code);
	void listeMaterielAlloue(User user);
	void listeMaterielAlloueParUser(User user); 
	void materielIndisponible(TypeMateriel typeMateriel, String code);
	void chercherMateriel(TypeMateriel typeMateriel, String code);
	void allouerMateriel(TypeMateriel typeMateriel, String code, User user); 
	void rendreMateriel(TypeMateriel typeMateriel, String code, User user);
	void listeMateriel(); 
}
