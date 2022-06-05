package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();
    Materiel findMateriel(Long id);
    String addMateriel(Materiel materiel);
    String supprimerMateriel(int id);
}
