package com.ensa.gi4.service.api;

import java.util.List;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.TypeMateriel;

public interface GestionMaterielServiceFacade {

    List<Materiel> afficherMateriel(TypeMateriel type);
	void ajouterNouveauMateriel(TypeMateriel type, Materiel materiel);
	void supprimerMateriel(TypeMateriel type, int id);
	Materiel findOne(TypeMateriel type,String nom);
	void modifierMateriel(TypeMateriel type,int id,Materiel materiel);
	void louerMateriel(TypeMateriel type,String nomMateriel,int userId);
	void rendreMateriel(TypeMateriel type,String nomMateriel,int userId);
	void markerNonDisponible(TypeMateriel type,String nomMateriel);
}
