package com.ensa.gi4.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.ensa.gi4.datatabase.impl.MaterielDaoImpl;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;
import com.ensa.gi4.service.impl.GestionMaterielServiceImpl;

public class GestionMaterielServiceTest {
private MaterielDaoImpl dao;
Materiel materiel;
Materiel materiel_louer;
Utilisateur user;

@BeforeEach
void setUp() {
	DriverManagerDataSource datasource = new DriverManagerDataSource();
	datasource.setUrl("jdbc:h2:~/h2/material_db");
	datasource.setUsername("sa");
	datasource.setPassword("sa");
	datasource.setDriverClassName("org.h2.Driver");
	dao = new MaterielDaoImpl();
	long id_M=100;
	long id_M1=101;
	long id_user=1111;
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
//Creation user
	user.setRole("admin");
	user.setUsername("hamid");
	user.setId_utilisateur(id_user);
	//Creation materiel par default louer
	materiel_louer.setAlloue(true);
	materiel_louer.setCode("KKK100");
	materiel_louer.setDisponible(false);
	materiel_louer.setDuree("5 jours");
	materiel_louer.setId(id_M1);
	materiel_louer.setId_user(id_user);
	materiel_louer.setName("LIVRE");
	


	
}

@AfterEach
void tearDown() {
	dao=null;
	materiel=null;
	materiel_louer=null;
	user=null;
}
@Test
void testFindAll() {
	// on a un list pas vide 
	List<Materiel> listMateriel = dao.findAll();
	assertTrue(!listMateriel.isEmpty());
}
@Test 
void testFindOne() {
	long id = 100;
	Materiel materiel = dao.findone(id);
	assertNotNull(materiel);
	//il faut etre non null, car on a crée ce materiel avec id=100 sur setUp
	
}
@Test
void testCreate() {
	long id_M=200;
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
void testUpdatee() {
	long id=100;
	//faire modifier le code pour le materiel que on a créé sur setUp
	//modifier ancier code KKK100 par KKK100_update pour materiel de id=100 que est crée sur setUp
	dao.update("KKK100_update", id);
}

@Test
void testDelet() {
	long id=100;
	dao.delete(id);
}
@Test 
void testListAllouer() {
	List<Materiel> list = dao.listeMaterielAlloue(user.getId_utilisateur());
	assertTrue(list.isEmpty());
	//normalement true car oui il ya un  materiel  	allouer pour ce utilisitateur crée par defaut sur setUp
}
@Test
void tesrListAllouerAll() {
	List<Materiel> list = dao.listeMaterielAlloueAll();
	assertTrue(list.isEmpty());
	//list is not empty
	
	
}
@Test 
void allouermaterielTest() {
	dao.allouerMateriel(materiel.getId(), "5J", user.getId_utilisateur(), "hamid");
}
 @Test
 void testRendreMateriel() {
	 dao.rendreMateriel(materiel_louer.getId(), user.getId_utilisateur());
 }

@Test
void RendreMaterielIndisponibleTest() {
	dao.rendre_Materiel_indisponible(materiel.getId());
	//rendre materiel avec id=100 indisponible
}

}
