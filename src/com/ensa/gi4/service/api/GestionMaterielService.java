package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.AllocatedItem;
import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface GestionMaterielService {
    void init();
    Materiel findOneById(int id);
    List<Materiel> showAll();
    void addNew(Materiel materiel);
    void editMateriel(Materiel materiel);
    void deleteMateriel(Materiel materiel);
    Boolean toAllocate(int id,int id_user);
    Boolean toReturn(int id,int id_user);
    void editAvailable(int id,String change);
    Boolean isAvailable(int id);
    List<AllocatedItem> showAllAllocatedItems();
    List<AllocatedItem> showUserAllocatedItems(int id_user);
}
