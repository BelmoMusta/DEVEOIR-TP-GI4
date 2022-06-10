package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {
	
    int ajouterNouveauMateriel(Materiel materiel);
    int supprimerMateriel(String code);
    int modifierMateriel(String code, Integer stock, String ancienCode);
    int materielIndisponible(String code); 
    
}
