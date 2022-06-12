package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;

public interface AllocationService {
    void allouerMateriel(Utilisateur utilisateur, Materiel materiel, String duree);
    void rendreMateriel(Utilisateur utilisateur, Materiel materiel);
    void listerMaterielAllou(int idUser);

    void listerTouTAllocation();
}
