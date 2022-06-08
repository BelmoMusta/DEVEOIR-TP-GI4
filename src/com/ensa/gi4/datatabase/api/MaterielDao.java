package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();
    Materiel findOne(Long id);
    void addMateriel(Materiel materiel);
    void deleteMateriel(Long id);
    void marquerMaterielIndisponible(Long id);
    void allouerMateriel(Long idMateriel, String dure, Long idUtilisateur, String usernameUtilisateur);
    void rendreMateriel(Long idMateriel);
    void modifierMateriel(Long id, String code);
    List<Materiel> listeMaterielAlloue(Long id);
    List<Materiel> listeMaterielAlloueAll();
}
