package com.ensa.gi4.service.api;

public interface MaterialsManagingService {
    void addMaterial();
    void deleteMaterial();
    void updateMaterial();
    void listAllMaterials();
    void showMaterial();
    void borrowMaterial(int userId);
    void returnMaterial(int userId);
    void showMaterialsBorrowedByMe(int userId);
    void showMaterialsBorrowedByAUser();
}
