package com.ensa.gi4.database.api;

import com.ensa.gi4.modele.Material;
import com.ensa.gi4.modele.MaterialType;

import java.util.List;

public interface MaterialDao {

    List<Material> findAll();
    Material findOne(Long id);
    List<Material> findMaterialByType(Long id);
    int createNewMaterial(Material material);
    int deleteMaterial(Long id);
    int updateOne(Material material);
}
