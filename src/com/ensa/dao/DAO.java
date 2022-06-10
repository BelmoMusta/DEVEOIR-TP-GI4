package com.ensa.dao;

import com.ensa.modele.Livre;
import com.ensa.modele.Materiel;

import java.util.List;

public interface DAO<T> {
    //pour les Livres:


    List<T> findAll();

    Materiel findOne(Long id);



}
