package com.ensa.gi4.database.api;

import com.ensa.gi4.modele.MaterialType;

import java.util.List;

public interface MaterialTypeDao {

    MaterialType findOne(Long id);
    List<MaterialType> findAll();
    Long createNewMaterialType(String name);

}
