package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
	List<Materiel> findAll();

	Materiel findOne(Long id);

	int quantiteMateriel(String code);

	boolean estDisponible(Long id);
	
	boolean idMaterielExiste(Long id);
     
	Materiel nomMatereielExiste(String nom);

	List<Materiel> listerMaterielsAlloue();

	void ajouterMateriel(Materiel materiel);

	void supprimmerMateriel(Long id);

	void modifierMateriel(Long id, String code);

	void marquerMaterielIndisponible(Long id);

	void afficherMaterielAlloueParUtilisateur();

	String allouerMateriel(String nom, String duree);

	boolean rendreMateriel(Long id);

}
