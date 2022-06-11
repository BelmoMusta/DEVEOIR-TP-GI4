package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();

    Materiel findOne(Long id);

    int insertMateriel(Materiel materiel);

    int deleteMateriel(Long id);
    int marquerDisponible(int dispo,Long id);
   int updateMateriel(String code,Long id);
    int allouéMateriel(int alloué,Long employee, Long id);
    int rendreMateriel(int alloué,Long employee, Long id);
    List<Materiel> ListeParchacun(Long id);
    List<Materiel> ListeParLui();
}
