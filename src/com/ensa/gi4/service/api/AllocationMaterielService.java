package com.ensa.gi4.service.api;

import java.util.List;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.TypeMateriel;
import com.ensa.gi4.modele.User;

public interface AllocationMaterielService {

	Materiel chercherMateriel(TypeMateriel typeMateriel, String code); 
	void allouerMateriel(TypeMateriel typeMateriel, String code, User user); 
	void rendreMaterielAlloue(TypeMateriel typeMateriel, String code, User user); 
	List<Materiel> listeMaterielAlloue(User user);
	void listeMaterielAlloueParUser(); 
	List<Materiel> listeMateriel(); 
}
