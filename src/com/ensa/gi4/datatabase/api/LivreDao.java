package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Livre;

import java.util.List;
import java.util.Map;

public interface LivreDao {
    List<Livre> findAll();
    Livre findOne(Integer id);
    void add(Livre livre);
    void update(Livre livre);
    Map getDetails(Integer id);
}
