package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    
    List<Materiel> findAll();

    Materiel findOne(String id);
    
    public Materiel findOneById(int id);
    
    int addOne(Materiel materiel);

    void removeOne(String code);
    
    void updateOne(Materiel materiel);
}
