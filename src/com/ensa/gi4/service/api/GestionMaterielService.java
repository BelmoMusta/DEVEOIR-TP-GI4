package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Material;
import com.ensa.gi4.modele.User;

public interface GestionMaterielService {
    void init();
    void AddNewMaterial(Material material);
    void SearchMaterial(long materialId);
    void EditMaterial(long materialId, String name, String materialType);
    void ListAllMaterial();
    void DeleteMaterial(long materialId);
    void AllocateMaterial(long materialId, long userId);
    void ReturnMaterial(long materialId);
    void ListUserAllocatedMaterialToUser(long userId);
    void ListAllocatedMaterial();
    void MarkNotAvailable(long materialId);
}
