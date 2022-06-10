package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {

	
	//Les requêtes SQL
	String queryAfficherMateriel = "SELECT * FROM materiel;";
	String queryChercherMateriel = "SELECT * FROM materiel WHERE id=?;";
	String queryAfficherMaterielUtilisateur = "SELECT * FROM materiel INNER JOIN materiel.id = materiel_utilisateur.idMateriel;";
	String queryAllouerMateriel1 = "INSERT INTO materiel_utilisateur(idUser, idMateriel) VALUES(?, ?);";
	String queryAllouerMateriel2 = "UPDATE materiel SET etat=? WHERE id=?;";
	String ajoutNouveauMateriel = "INSERT INTO materiel(id, name, code, etat) VALUES (?,?,?,?)";
	String querySupprimerMateriel = "DELETE FROM materiel WHERE id=?;";
	String queryModifierMateriel = "UPDATE materiel SET name=?, code=?, etat=? WHERE id=?;";
	String queryAfficherMaterielEveryUser = "SELECT materiel.name, materiel.code "
											+ "FROM materiel, materiel_utilisateur "
											+ "WHERE materiel.id = materiel_utilisateur.idMateriel"
											+ "GROUP BY idUser;";
	
	//La méthode d'affichage des matériels
	@Override
	public List<Materiel> afficherMateriel() {
		
		return super.afficherMateriel(queryAfficherMateriel);
	}
	
	//La méthode de recherche d'un matériel
	@Override
	public Materiel chercherMateriel(Long idMateriel) {
		
		return super.chercherMateriel(queryChercherMateriel, idMateriel);
	}
	
	//La méthode d'affichage de matériels allouées par un utilisateur
	@Override
	public List<Materiel> afficherMaterielUser(Long idUser) {
		
		return super.afficherMaterielUser(queryAfficherMaterielUtilisateur, idUser);
	}
	
	//La méthode d'allocation de matériel
	@Override
	public int allouerMateriel(Long idMateriel, String etat, Long idUser) {
		
		etat = "Alloue";
		
		ajouterMaterielUser(idUser, idMateriel);
		
		return super.allouerMateriel(queryAllouerMateriel2, etat, idMateriel);
	}
	
	//La méthode d'ajout des identifiants dans la table intermediaire lors de l'allocation
	public int ajouterMaterielUser(Long idUser, Long idMateriel) {
		
		return super.ajouterMaterielUser(queryAllouerMateriel1, idUser, idMateriel);
	}
	
	//La méthode de restitution du matériel
	@Override 
	public int rendreMateriel(Long idMateriel, String etat) {
		
		etat = "Restitue";
		
		return super.rendreMateriel(queryAllouerMateriel2, etat, idMateriel);
	}
	
	//La méthode d'ajout d'un nouveau matériel
	@Override
	public int ajouterNouveauMateriel(Materiel materiel) {
		
		return super.ajouterNouveauMateriel(ajoutNouveauMateriel, materiel);
	}
	
	//La méthode de suppression d'un matériel
	@Override
	public int supprimerMateriel(Long idMateriel) {
		
		return super.supprimerMateriel(querySupprimerMateriel, idMateriel);
	}
	
	//La méthode de modification d'un matériel
	@Override
	public int modifierMateriel(Materiel materiel, Long idMateriel) {
		
		return super.modifierMateriel(queryModifierMateriel, materiel, idMateriel);
	}
	
	//La méthode pour rendre un matériel indisponible
	@Override
	public int materielIndisponible(Long idMateriel, String etat) {
		
		etat = "Indisponible";
		
		return super.materielIndisponible(queryAllouerMateriel2, etat, idMateriel);
	}
	
	//La méthode d'affichage de matériels pour chaque utilisateur
	@Override
	public List<Materiel> afficherMaterielEveryUser() {
		
		return super.afficherMaterielEveryUser(queryAfficherMaterielEveryUser);
	}


    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
}
