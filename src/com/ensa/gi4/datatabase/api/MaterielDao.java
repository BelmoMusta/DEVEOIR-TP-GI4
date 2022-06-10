package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.MaterielAllocated;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();

    Materiel findOne(int id);

    void addMateriel(Materiel materiel);

    void editMateriel(int id, Materiel materiel);

    void editMaterielAvailability(int id, Materiel materiel);

    void deleteMateriel(int id);

    void allocateMateriel(Materiel materiel, int userID, int quantity, boolean ifExist, int materielAllocatedQuantity);

    void returnMateriel(Materiel materiel, MaterielAllocated materielAllocated, int quantity);

}
