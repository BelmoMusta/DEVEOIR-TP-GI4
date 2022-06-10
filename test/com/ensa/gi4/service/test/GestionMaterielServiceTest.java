package com.ensa.gi4.service.test;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;

import junit.framework.TestCase;

public class GestionMaterielServiceTest extends TestCase {
	
	@Autowired
	GestionMaterielService materielService;
	
	int check = 0;
	Materiel materiel;
	Materiel materielRecherche;
	List<Materiel> listMateriel;
	List<Materiel> listMaterielUser;
	List<Materiel> listMaterielEveryUser;
	boolean exist = false;
	
	//La méthode SetUp() : Initialisation des ressources
	protected void setUp() {
		
		materiel.setName("Chaise");
		materiel.setCode("CH");
		materiel.setEtat("Restitue");
		listMateriel = null;
		listMaterielUser = null;
		listMaterielEveryUser = null;
		materielRecherche = null;
	}
	
	//Les méthodes de test
	public void testInit() {
		
		
		
	}
	
	public void testChercherMaterielS() {
		
		materielRecherche = materielService.chercherMaterielS(8L);
		
		if(materielRecherche.equals(null)) {
			
			exist = false;
		
		}else {
			
			exist = true;
		}
		
		assertEquals("Le retour de la méthode de recherche d'un matériel", exist, true);
	}
	
	public void testAllouerMaterielS() {
		
		check = materielService.allouerMaterielS(1L, "Alloue", 5L);
		
		assertEquals("Le retour de la méthode d'allocation d'un matériel", check, 1);
	}
	
	public void testRendreMaterielS() {
		
		check = materielService.rendreMaterielS(2L, "Restitue");
		
		assertEquals("Le retour de la méthode de restitution d'un matériel", check, 1);
	}
	
	public void testAfficherMaterielS() {
		
		listMateriel = materielService.afficherMaterielS();
				
		assertEquals("Le retour de la méthode d'affichage de la liste des matériels", listMateriel, 123/*une liste de matériels saisie manuellement*/);
	}
	
	public void testAfficherMaterielUserS() {
		
		listMaterielUser = materielService.afficherMaterielUserS(5L);
		
		assertEquals("Le retour de la méthode d'affichage de la liste des matériels d'un utilisateur", listMaterielUser, 123/*une liste de matériels saisie manuellement*/);
	}
	
	public void testAjouterNouveauMaterielS() {
		
		check = materielService.ajouterNouveauMaterielS(materiel);
		
		assertEquals("Le retour de la méthode d'ajout d'un nouveau matériel", check, 1);
	}
	
	public void testSupprimerMaterielS() {
	
		check = materielService.supprimerMaterielS(4L);
		
		assertEquals("Le retour de la méthode de suppression d'un matériel", check, 1);
	}
	
	public void testModifierMaterielS() {
		
		check = materielService.modifierMaterielS(materiel, 6L);
		
		assertEquals("Le retour de la méthode de modification d'un matériel", check, 1);
		
	}
	
	public void testMaterielIndisponibleS() {
		
		check = materielService.materielIndisponibleS(3L, "Indisponible");
		
		assertEquals("Le retour de la méthode d'ajout d'un nouveau matériel", check, 1);
		
	}
	
	public void testAfficherMaterielEveryUserS() {
		
		listMaterielEveryUser = materielService.afficherMaterielEveryUserS();
		
		assertEquals("Le retour de la méthode d'affichage de la liste des matériels de chaque utilisateur", listMaterielEveryUser, 123/*une liste de matériels saisie manuellement*/);
		
	}
	
	
	//La méthode tearDown() : Libération des ressources
	protected void tearDown() {
		
		materiel = null;
		listMateriel = null;
		listMaterielUser = null;
		listMaterielEveryUser = null;
		materielRecherche = null;
	}
	

}
