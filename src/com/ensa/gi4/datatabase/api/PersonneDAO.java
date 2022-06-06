package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Personne;

public interface PersonneDAO {
Personne findPersonne(String nom, String pw);
void allouerMateriel(String code,String duree);
boolean verifierExistanceAllocation(String code);
void rendreMateriel(String code);
}
