package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.MaterielAllocated;

import java.util.List;

public interface MaterielAllocatedDao {
    List<MaterielAllocated> findAll();
    List<MaterielAllocated> listerMesMateriels(int id);
}
