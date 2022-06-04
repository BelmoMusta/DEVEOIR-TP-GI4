package com.ensa.gi4.datatabase.api;

import java.util.List;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.TypeMateriel;

public interface MaterielDao {
    List<Materiel> findAll();

    Materiel findOne(Long id);
    
    int ajouter(Materiel materiel);
    List<Materiel> listMaterialByType(TypeMateriel typeMateriel);  
    int updateMateriel(String code, Integer stock, String ancienCode); 
    int supprimerMateriel(String code); 
    int materielIndisponible(String code); 

}
