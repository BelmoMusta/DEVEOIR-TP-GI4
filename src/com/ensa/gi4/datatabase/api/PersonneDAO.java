package com.ensa.gi4.datatabase.api;

import java.util.List;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Personne;

public interface PersonneDAO {
Personne findPersonne(String nom, String pw);
boolean allouerMateriel(String code,String duree);
//boolean verifierExistanceAllocation(String code);
boolean rendreMateriel(int id);
Personne getPersonneConnecte() ;
String determinerRole();
}
