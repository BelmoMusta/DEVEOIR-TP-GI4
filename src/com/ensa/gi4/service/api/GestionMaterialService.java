package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Material;
import com.ensa.gi4.modele.MaterialType;
import com.ensa.gi4.modele.RentedMaterial;
import com.ensa.gi4.modele.User;

import java.util.List;

public interface GestionMaterialService {

    List<Material> showMaterialList();
    Material searchMaterialById(Long id);
    List<Material> searchMaterialByType(Long materialTypeId);
    List<MaterialType> getAllMaterialType();
    Long addNewMaterialType(String name);
    int addNewMaterial(Material material);
    int deleteMaterial(Long materialIdToBeDeleted);
    int editMaterial(Material materialToBeEdited);
    boolean isAtLeastOneMaterialIsRented(Material material);
    int rentOneMaterial(RentedMaterial materialRentRecord);
    List<RentedMaterial> getUserRentedMaterials(Long userId);
    int returnRentedMaterial(Long returnedMaterialId);
    int makeMaterialAvailableOrUnavailable(Material material);
    List<User> getRentedMaterialsByUsers();
    Integer getNumberOfMaterialToReturnToday(Long userId);
    boolean canMaterialBeRented(Material materialToBeRented);
}
