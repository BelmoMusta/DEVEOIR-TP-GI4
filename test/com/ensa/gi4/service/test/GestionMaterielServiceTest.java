package com.ensa.gi4.service.test;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;

import junit.framework.TestCase;

public class GestionMaterielServiceTest extends TestCase {
	
	int check = 0;
	Materiel materiel;
	Materiel materielRecherche;
	List<Materiel> listMateriel;
	List<Materiel> listMaterielUser;
	List<Materiel> listMaterielEveryUser;
	boolean exist = false;
	
	@Autowired
	GestionMaterielService materielService;
	
	
	
	//La m�thode SetUp() : Initialisation des ressources
	protected void setUp() {
		
		materiel.setName("Chaise");
		materiel.setCode("CH");
		materiel.setEtat("Restitue");
		listMateriel = null;
		listMaterielUser = null;
		listMaterielEveryUser = null;
		materielRecherche = null;
	}
	
	//Les m�thodes de test
	public void testInit() {
		
		
		
	}
	
	public void testChercherMaterielS() {
		
		materielRecherche = materielService.chercherMaterielS(8L);
		
		if(materielRecherche.equals(null)) {
			
			exist = false;
		
		}else {
			
			exist = true;
		}
		
		assertEquals("Le retour de la m�thode de recherche d'un mat�riel", exist, true);
	}
	
	public void testAllouerMaterielS() {
		
		check = materielService.allouerMaterielS(1L, "Alloue", 5L);
		
		assertEquals("Le retour de la m�thode d'allocation d'un mat�riel", check, 1);
	}
	
	public void testRendreMaterielS() {
		
		check = materielService.rendreMaterielS(2L, "Restitue");
		
		assertEquals("Le retour de la m�thode de restitution d'un mat�riel", check, 1);
	}
	
	public void testAfficherMaterielS() {
		
		listMateriel = materielService.afficherMaterielS();
				
		assertEquals("Le retour de la m�thode d'affichage de la liste des mat�riels", listMateriel, 123/*une liste de mat�riels saisie manuellement*/);
	}
	
	public void testAfficherMaterielUserS() {
		
		listMaterielUser = materielService.afficherMaterielUserS(5L);
		
		assertEquals("Le retour de la m�thode d'affichage de la liste des mat�riels d'un utilisateur", listMaterielUser, 123/*une liste de mat�riels saisie manuellement*/);
	}
	
	public void testAjouterNouveauMaterielS() {
		
		check = materielService.ajouterNouveauMaterielS(materiel);
		
		assertEquals("Le retour de la m�thode d'ajout d'un nouveau mat�riel", check, 1);
	}
	
	public void testSupprimerMaterielS() {
	
		check = materielService.supprimerMaterielS(4L);
		
		assertEquals("Le retour de la m�thode de suppression d'un mat�riel", check, 1);
	}
	
	public void testModifierMaterielS() {
		
		check = materielService.modifierMaterielS(materiel, 6L);
		
		assertEquals("Le retour de la m�thode de modification d'un mat�riel", check, 1);
		
	}
	
	public void testMaterielIndisponibleS() {
		
		check = materielService.materielIndisponibleS(3L, "Indisponible");
		
		assertEquals("Le retour de la m�thode d'ajout d'un nouveau mat�riel", check, 1);
		
	}
	
	public void testAfficherMaterielEveryUserS() {
		
		listMaterielEveryUser = materielService.afficherMaterielEveryUserS();
		
		assertEquals("Le retour de la m�thode d'affichage de la liste des mat�riels de chaque utilisateur", listMaterielEveryUser, 123/*une liste de mat�riels saisie manuellement*/);
		
	}
	
	
	//La m�thode tearDown() : Lib�ration des ressources
	protected void tearDown() {
		
		materiel = null;
		listMateriel = null;
		listMaterielUser = null;
		listMaterielEveryUser = null;
		materielRecherche = null;
	}
	

}
