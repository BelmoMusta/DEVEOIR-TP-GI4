package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.RentedMaterial;
import com.ensa.gi4.modele.User;

import java.util.List;
import java.util.Map;

public interface RentedDao {
    List<RentedMaterial> viewRentedMaterialsByEachUser(User user);
    List<RentedMaterial> viewRentedMaterialsOfAllUsers();
    int checkNumberRentedMaterial(User user);
    int addMateriel(RentedMaterial rentedMaterial);

}
