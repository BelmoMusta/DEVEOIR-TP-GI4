package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();

    Materiel findone(Long i);
    void create(Materiel t);
    void update(String Name,String Code, Long id) ;
    void delete(Long id);
    void allouerMateriel(Long id_M, String dure, Long id_user, String user_name);
    void rendreMateriel(Long id_M);
    void rendre_Materiel_indisponible(Long id);
    List<Materiel> listeMaterielAlloue(Long id);
    List<Materiel> listeMaterielAlloueAll();
    

}
