package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface GestionMaterielService {
    void init();
    List<Materiel> findAll();
    Materiel findOne(Long id);
    void addMaterial(Materiel materiel);
    void deleteMaterial(Long id);
    void updateMaterial(String name,String code,Integer stock,Long id);
    void updateMaterial(Materiel materiel);
    public void makeMaterialUnavailable(Long id);
}
