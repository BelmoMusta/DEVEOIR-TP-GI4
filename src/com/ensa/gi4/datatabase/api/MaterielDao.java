package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Material;

import java.util.List;

public interface MaterielDao {
    List<Material> findAll();

    Material findOne(Long id);

}
