package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
	List<Materiel> findAll();

	Materiel findOne(Long id);

	int quantiteMateriel(String code);

	boolean estDisponible(String nom);

	Materiel nomMatereielExiste(String nom);

	List<Materiel> listerMaterielsAlloue();

	boolean ajouterMateriel(Materiel materiel);

	boolean supprimmerMateriel(int id);

	// void diminuerQuantite(String code);
	// void augmenterQuantite(String code);
	boolean modifierMateriel(int id, String nom, String code);

}
