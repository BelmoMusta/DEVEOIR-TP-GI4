package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

public interface GestionMaterielService {
    void init();
    void listerMateriel();
    void ajouterNouveauMateriel(Materiel materiel);
    public void chercherMateriel(int id);
    public void supprimerMateriel(int id);
    public void modifierMateriel(int id);
    public void allouerMateriel(int IdUser, int idMateriel);
    public void listerMaterielAlloues();
    public void listerMaterielAllouerChaqueUser();
   public void listerMaterielAllouerUser(int id);
   public void rendreMateriel(int id);
   public Boolean isFree(int id);
    
}
