package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

public interface GestionMaterielService {
    void init();
    void listerMateriel();
    void ajouterNouveauMateriel(Materiel materiel);
    void supprimerMateriel(int id);
    void chercherrMateriel(int id);
    void modifierMateriel(int id);
    void allouerMateriel(int id, User user, int quantity);
    void returnMateriel(int id, int userId);
    void listerAllAllocations();
    void listerAllocationsUser(int id);
    void editAvailability(Materiel materiel);

}
