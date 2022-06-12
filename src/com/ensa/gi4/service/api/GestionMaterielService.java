package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.RentedMaterial;
import com.ensa.gi4.modele.User;

import java.util.List;

public interface GestionMaterielService {
    void init();
    List<Materiel> listMaterial();
    int addNewMaterial(Materiel materiel);
    Materiel searchMaterialByName(String name);
    Materiel seachMaterialById(Long id);
    int deleteMaterial(Materiel materiel);
    int editMateriel(Materiel materiel,String name);
    void rentMateriel(Materiel materiel,User user);
    void returnMateriel(Materiel materiel,User user);
    void markUnavailable(Materiel materiel);
    List<RentedMaterial> viewRentedMaterielsByEachUser(User user);
    List<RentedMaterial> viewRentedOfAllUsers();
    int checkRentedBeforeRenting(User user);
    User searchUserById(Long id);
}
