package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();
    Materiel findOne(Long id);
   // Materiel allouerMateriel(String code);
    int quantiteMateriel(String code);
    boolean estDisponible(String code);
    Materiel codeMatereielExiste(String code);
    void diminuerQuantite(String code);

}
