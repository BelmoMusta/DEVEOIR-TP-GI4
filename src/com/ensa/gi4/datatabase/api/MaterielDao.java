package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.sql.Date;
import java.util.List;

public interface MaterielDao {
	List<Materiel> findAll();
	Materiel findOne(int id);
    void addMateriel(Materiel materiel);
    void deleteMateriel(int id);
    void marquerMaterielIndisponible(int id);
    boolean allouerMateriel(int idMateriel, Date dateDebut, Date dateFin, int idUtilisateur);
    void rendreMateriel(int idMateriel);
    void modifierMateriel(int id, String nom, String code);
}
