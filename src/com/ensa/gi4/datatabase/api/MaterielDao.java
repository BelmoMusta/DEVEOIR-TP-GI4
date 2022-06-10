package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();
    List<Materiel> findAllWithCriteria(List<String> conditions, List<String> values);
    Materiel findOne(Integer id);
    boolean isAvailable(Integer id);
    void allocate(Integer id);
    void deallocate(Integer id);
    void delete(Integer id);
    void update(int id, String[] fields, String[] values);
    Integer findMaxId();
}
