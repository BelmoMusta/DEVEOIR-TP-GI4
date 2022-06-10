package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.AllocatedItem;

import java.util.List;

public interface AllocatedItemDao {
    List<AllocatedItem> findAll();
    List<AllocatedItem> findByUser(int id_user);
    void addItem(int id_user,int id_materiel);
    void deleteItem(int id_user,int id_materiel);
}
