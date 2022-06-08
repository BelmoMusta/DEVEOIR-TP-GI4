package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();

    Materiel findOne(Long id);
    void ajouterNvMateriel(Materiel p);
    Materiel matereielExiste(String nom) ;
    void supprimerMateriel(Long id);
    boolean modifierInfosMateriel(Long id, String nom, String code) ;
    boolean indisponibleMateriel(Long id);
    int quantiteMateriel(String name);
    
    
    

}
