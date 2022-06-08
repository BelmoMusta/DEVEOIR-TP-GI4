package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
	void add(Materiel materiel);
    List<Materiel> findAll();
    Materiel findOne(String nom);
    Materiel findOne(int id);
    void delete(int id);
	void update(int id,Materiel materiel);
	void louerMateriel(String nomMateriel,int userId);
	void rendreMateriel(String nomMateriel,int userId);
	void markerNonDisponible(String nomMateriel);
}
