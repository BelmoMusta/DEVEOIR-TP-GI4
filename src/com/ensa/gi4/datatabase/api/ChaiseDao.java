package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;

import java.util.List;
import java.util.Map;

public interface ChaiseDao {
    List<Chaise> findAll();
    Chaise findOne(Integer id);
    void add(Chaise chaise);
    void update(Chaise chaise);
    Map getDetails(Integer id);
}
