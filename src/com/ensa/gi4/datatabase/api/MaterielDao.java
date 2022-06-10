package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();
    Materiel findOne(int id);
    void add(Materiel m);
    void delete(int id);
    void edit(int id, String newName,int stock);
    int getItemStock(int id);
    int getItemAllocated(int id);
    Boolean isAllocated(int id);
    Boolean isAvailable(int id);
    void editAvailable(int id,String change);
    void decreaseStock(int id);
    void increaseStock(int id);




}
