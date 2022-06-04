package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();
    Materiel findOne(Long id);
    boolean isAllocated(Long id);
    void allocate(Long id);
    void delete(Long id);

}
