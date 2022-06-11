package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();

    Materiel findOne(Long id);
    int ajouterMateriel(Materiel materiel);
  int supprimerMateriel(Long id);
int modifierNomMateriel(String nom,Long id);
int modifierCodeMateriel(String code,Long id);
Materiel chercherMateriel(Long id);
int rendreMaterielIndisponible(Long id);
int allouerMateriel(String name,boolean b1,boolean b2,Long id);
int rendreMateriel(boolean b1,boolean b2,Long id);


}
