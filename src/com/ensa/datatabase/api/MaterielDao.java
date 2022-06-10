package com.ensa.datatabase.api;

import com.ensa.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();

    Materiel findOne(Long id);
    // compléter par les autres méthodes
}
