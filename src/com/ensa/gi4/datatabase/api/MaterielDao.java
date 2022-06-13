package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();
    Materiel findOne(Long id);
    int createNewMaterial(Materiel materiel);
    int deleteMaterial(Long id);
    int editMaterial(Long id,String name);
    Materiel findMaterielByName(String name);
    void rentMateriel(Materiel materiel, User user);
    void putBackMateriel(Materiel materiel, User user);
    void markUnavailable(Materiel materiel);
}
