package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Personne;

public interface PersonneDAO {
Personne findPersonne(String nom, String pw);
Personne allouerMateriel(String code,String duree);
}
