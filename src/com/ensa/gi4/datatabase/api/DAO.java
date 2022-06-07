package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Material;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<T> getAll();
    Optional<T> getById(int id);
    void delete(int id);
    void update(int id, T t);
    void add(T t);
}
