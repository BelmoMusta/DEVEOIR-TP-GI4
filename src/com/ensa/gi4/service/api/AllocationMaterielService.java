package com.ensa.gi4.service.api;

import java.util.List;
import java.util.Optional;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.TypeMateriel;
import com.ensa.gi4.modele.User;

public interface AllocationMaterielService {

	Optional<Materiel> chercherMateriel(TypeMateriel typeMateriel, String code); 
	int allouerMateriel(TypeMateriel typeMateriel, String code, User user); 
	int rendreMaterielAlloue(TypeMateriel typeMateriel, String code, User user); 
	Optional<List<Materiel>> listeMaterielAlloue(User user);
	void listeMaterielAlloueParUser(); 
	Optional<List<Materiel>> listeMateriel(); 
}
