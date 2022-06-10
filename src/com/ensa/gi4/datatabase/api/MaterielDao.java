package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();
    Materiel findOneMateriel(String code);
    Materiel RechercheMateriel(String code);
    void AjouterMaterial(Materiel m);
    void SupprimerMateriel(String code);
    void ModifierMateriel(String LCode, String NCode);
    void allouerMateriel(Materiel materiel, User user);
    void ModifierDisponibilite(Materiel meteriel);
    void supprimerMaterielAlloue(Materiel materiel);
    List<Allocation> findListAlloueByName(String username);
    List<Allocation> findAllListAlloue();
    Allocation findMaterielAlloue(Materiel materiel);
}
