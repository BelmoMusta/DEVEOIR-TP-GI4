package com.ensa.gi4.service.api;

import java.util.List;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {
	
	//La liste des méthodes à implémenter
	public void init();
	public Materiel chercherMaterielS(Long idMateriel);
	public int allouerMaterielS(Long idMateriel, String etat, Long idUser);
	public int rendreMaterielS(Long idMateriel, String etat);
	public List<Materiel> afficherMaterielS();
	public List<Materiel> afficherMaterielUserS(Long idUser);
	
	public int ajouterNouveauMaterielS(Materiel materiel);
	public int supprimerMaterielS(Long idMateriel);
	public int modifierMaterielS(Materiel materiel, Long idMateriel);
	public int materielIndisponibleS(Long idMateriel, String etat);
	public List<Materiel> afficherMaterielEveryUserS();
	

}
