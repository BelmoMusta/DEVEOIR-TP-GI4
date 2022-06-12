package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Allocations;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();

    Materiel findOne(int id);

    void addMateriel(Materiel materiel);

    void deleteMateriel(int id);

    void editMateriel(int id, Materiel materiel);

    void returnMateriel(Materiel materiel, Allocations allocation, int availability);

    void editAvailability(int id, Materiel materiel);

    void allocateMateriel(Materiel materiel, User user, int availability, int allocationQuantity);

}
