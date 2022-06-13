package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();
    Materiel findOne(Integer id);
    void delete(Integer id);
    void allocate(Materiel materiel);
    void deallocate(Materiel materiel);
}
