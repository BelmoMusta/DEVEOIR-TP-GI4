package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();
    Materiel findOne(Long id);
   int aadd(Materiel materiel);
    int deleteMateriel(String code);
    int updateMateril(String code, String newCode);
  int allouerMateriel(int alloue,Long idUser ,Long idMat);
    int marquerDisponible(int dispo, Long id);
    int epuise(int dispo, Long id);
    int renderMaterial(int alloue,Long idUser ,Long idMat);
}
