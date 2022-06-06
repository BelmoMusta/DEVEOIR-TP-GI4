package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();
    Materiel findOne(int id);
    void delete(int id);
    void edit(int id, String newName);
    Boolean isAllocated(int id);
    void toAllocate(int id);



}
