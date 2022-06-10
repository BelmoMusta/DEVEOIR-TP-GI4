package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Allocation;

import java.util.List;

public interface MaterielDao {
    List<Materiel> getAllMateriels();

    Materiel getMaterielByName(String name);

    void addMateriel(Materiel materiel);

    void deleteMateriel(String nom);

    void editMaterielName(String oldName, String newName);

    void editMaterielCode(String name, String code);

    void editMaterielAvailability(String name, boolean availability);

    void editMaterielQuantity(String name, int quantity);

    void allocateMateriel(Materiel materiel, int userID, int quantity, boolean ifExist, int materielAllocatedQuantity);

    void returnMateriel(Materiel materiel, Allocation materielAllocated, int quantity, int userID);

}
