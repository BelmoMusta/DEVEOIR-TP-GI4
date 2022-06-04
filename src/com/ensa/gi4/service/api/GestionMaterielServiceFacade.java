package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.TypeMateriel;

public interface GestionMaterielServiceFacade {
	
	void ajouterNouveauMateriel(TypeMateriel type, String code, Integer stock);
	void modifierMateriel(TypeMateriel typeMateriel, String code, Integer stock, String ancienCode); 
	void supprimerMateriel(TypeMateriel typeMateriel, String code);
	void listeMaterielAlloue(); 
	void materielIndisponible(TypeMateriel typeMateriel, String code);
}
