package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

import java.util.List;

public interface GestionAllocationMaterielService {

    //void init();

    void allouerMateriel(User user, Long idMateriel);

    void rendreMaterielAlloue(User user, Long idMateriel);

    void listerMaterielAllouePourUser(User user);


}
