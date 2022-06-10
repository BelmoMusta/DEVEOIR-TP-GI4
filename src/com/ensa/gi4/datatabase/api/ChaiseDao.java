package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Chaise;

import java.util.List;

public interface ChaiseDao {
    List<Chaise> findAll();
    Chaise findOne(Integer id);
    Chaise save(Chaise chaise);

    Integer findMaxId();
}
