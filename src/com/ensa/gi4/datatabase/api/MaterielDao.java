package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
	List<Materiel> findAll();

	Materiel findOne(Long id);

	int quantiteMateriel(String code);

	boolean estDisponible(int id);
	boolean idMaterielExiste(int id);

	Materiel nomMatereielExiste(String nom);

	List<Materiel> listerMaterielsAlloue();

	boolean ajouterMateriel(Materiel materiel);

	boolean supprimmerMateriel(int id);

	boolean modifierMateriel(int id, String nom, String code);

	void marquerMaterielIndisponible(int id);

	void afficherMaterielAlloueParUtilisateur();

	boolean allouerMateriel(String code, String duree);

	boolean rendreMateriel(int id);

}
