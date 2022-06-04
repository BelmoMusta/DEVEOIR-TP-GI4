package com.ensa.gi4.service.api;

import java.util.List;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {
	
    void ajouterNouveauMateriel(Materiel materiel);
    void supprimerMateriel(String code);
    void modifierMateriel(String code, Integer stock, String ancienCode);
    void materielIndisponible(String code); 
    List<Materiel> listeMaterielAlloue(); 
}
