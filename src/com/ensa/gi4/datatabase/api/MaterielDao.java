package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();
    Materiel findOne(Long id);
  
    int quantiteMateriel(String code);
    boolean estDisponible(String nom);
    Materiel nomMatereielExiste(String nom);
  //  void diminuerQuantite(String code);
  //  void augmenterQuantite(String code);

}
