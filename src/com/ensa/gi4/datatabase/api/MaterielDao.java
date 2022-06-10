package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAllDAO();
    Materiel findOneDAO(Long id);
    void makeItUnavailable(Long id);
    void addMaterialDAO(String name,String code,Integer stock,Boolean disponibility);
    void deleteMaterialDAO(Long id);
    void updateMaterialDAO( String name,String code,Integer stock,Long id);
    void updateMaterialDAO( Materiel materiel);



}
