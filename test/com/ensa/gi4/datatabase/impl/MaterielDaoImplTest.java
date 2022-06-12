package com.ensa.gi4.datatabase.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;

class MaterielDaoImplTest {
	
private MaterielDaoImpl dao;
Materiel materiel;
Materiel materiel_louer;
Utilisateur user;

@SuppressWarnings("null")
@BeforeEach
void setUp() throws Exception {
	DriverManagerDataSource datasource = new DriverManagerDataSource();
	datasource.setUrl("jdbc:h2:~/h2/material_db");
	datasource.setUsername("sa");
	datasource.setPassword("sa");
	datasource.setDriverClassName("org.h2.Driver");
	dao = new MaterielDaoImpl(new JdbcTemplate(datasource));
	
	long id_M=100;
	long id_M1=101;
	
	long id_user=1111;
	//creation materiel 1 pas louer par defaut
	materiel=null;
	materiel_louer=null;
	user=null;
	
	materiel.setAlloue(true);
	materiel.setCode("KKK100");
	materiel.setDisponible(true);
	materiel.setDuree(null);
	materiel.setId(id_M);
	materiel.setId_user(null);
	materiel.setName("LIVRE");
	//creation user
	user.setRole("admin");
	user.setUsername("hamid");
	user.setId_utilisateur(id_user);
	//creation materiel par default louer 
	materiel_louer.setAlloue(true);
	materiel_louer.setCode("KKK100");
	materiel_louer.setDisponible(false);
	materiel_louer.setDuree("5 jours");
	materiel_louer.setId(id_M1);
	materiel_louer.setId_user(id_user);
	materiel_louer.setName("LIVRE");
	
	
}

@AfterEach
void tearDown() throws Exception {
	
	dao = null;
	materiel = null;
	materiel_louer=null;
	user=null;
	
	
	
}
	@Test
	void testFindAll() {
		//on a un list pas vide 
		List<Materiel> listMaterielTest = dao.findAll();
		assertTrue(!listMaterielTest.isEmpty());
	}

	@Test
	void testFindoneLong() {
		long id = 100;
		Materiel materiel = dao.findone(id);
		assertNotNull(materiel);
		//il faut etre non null car on a crée ce materiel avec
		// id = 100 sur setUp
		
	}

	@Test
	void testCreate() {
		long id_M = 200;
		Materiel materiel_ajouter = null ;
		materiel_ajouter.setAlloue(true);
		materiel_ajouter.setCode("KKK3");
		materiel_ajouter.setDisponible(true);
		materiel_ajouter.setDuree(null);
		materiel_ajouter.setId(id_M);
		materiel_ajouter.setId_user(null);
		materiel_ajouter.setName("LIVRE");
		dao.create(materiel);

		
		
	}

	@Test
	void testUpdate() {
		
		//faire modifier le code pour le produit que on a crée sur setUp
	 long id_M=100;
		materiel.setCode("KKK_update");
		
		
		
		
		dao.update("KKKUpdated", id_M);
		
	}

	@Test
	void testDelete() {
		//supprimer le produit 
		long id=100;
		dao.delete(id);
		
	}

	@Test
	void testListeMaterielAlloue() {
		
		List<Materiel> listMaterielallouer = dao.listeMaterielAlloue(user.getId_utilisateur());
		assertTrue(listMaterielallouer.isEmpty());
		//normalement true car oui il ya un  materiel  	allouer pour ce utilisitateur crée par defaut sur setUp
		
		
	}

	@Test
	void testListeMaterielAlloueAll() {
		List<Materiel> listMaterielallouer = dao.listeMaterielAlloueAll();
		assertFalse(listMaterielallouer.isEmpty());
		//listMaterielallouer is not empty
	}

	@Test
	void testAllouerMateriel() {
		
		


		dao.allouerMateriel(materiel.getId(), "5J", user.getId_utilisateur(), "hamid");
		
		
	}

	@Test
	void testRendreMateriel() {
		//Rendu le materiel_louer que a crée par defaut sur setUp (allouer pour hamid)
		dao.rendreMateriel(materiel_louer.getId(), user.getId_utilisateur());
		
	}

	@Test
	void testRendre_Materiel_indisponible() {
		//rendre materiel avec id=100 indisponible
		dao.rendre_Materiel_indisponible(materiel.getId());
		
	}

	

}
