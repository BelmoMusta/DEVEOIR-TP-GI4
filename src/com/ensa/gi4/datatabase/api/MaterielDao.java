package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
	
	//La signature des m�thodes � impl�menter
	public Materiel chercherMateriel(Long idMateriel);
	public int allouerMateriel(Long idMateriel, String etat, Long idUser);
	public int rendreMateriel(Long idMateriel, String etat);
	public List<Materiel> afficherMateriel();
	public List<Materiel> afficherMaterielUser(Long idUser);
	
	public int ajouterNouveauMateriel(Materiel materiel);
	public int supprimerMateriel(Long idMateriel);
	public int modifierMateriel(Materiel materiel, Long idMateriel);
	public int materielIndisponible(Long idMateriel, String etat);
	public List<Materiel> afficherMaterielEveryUser();

}
