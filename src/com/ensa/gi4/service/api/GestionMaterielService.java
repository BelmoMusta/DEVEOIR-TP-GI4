package com.ensa.gi4.service.api;

import java.util.List;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.TypeMateriel;

public interface GestionMaterielService {
	void add(Materiel materiel);
    List<Materiel> findAll();
    Materiel findOne(String nom );
    void delete(int id);
	void update(int id,Materiel materiel);
	void louerMateriel(String nomMateriel,int userId);
	void rendreMateriel(String nomMateriel,int userId);
	void markerNonDisponible(String nomMateriel);
}
