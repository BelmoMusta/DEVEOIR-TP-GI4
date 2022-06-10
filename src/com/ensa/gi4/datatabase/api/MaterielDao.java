package com.ensa.gi4.datatabase.api;

import java.util.List;
import java.util.Optional;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

public interface MaterielDao {
    Optional<List<Materiel>> findAll();
    Optional<Materiel> findOne(String code, String name);
    int ajouter(Materiel materiel); 
    int updateMateriel(String code, Integer stock, String ancienCode); 
    int supprimerMateriel(String code); 
    int materielIndisponible(String code); 
    int allouerMateriel(String code, String name, User user);
    int rendreMateriel(String code, String name, User user); 
    Optional<List<Materiel>> listeMaterielAlloue(User user); 
    
}
