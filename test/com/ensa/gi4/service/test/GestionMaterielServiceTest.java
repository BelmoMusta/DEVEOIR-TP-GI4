package com.ensa.gi4.service.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ensa.gi4.DatabaseConfig;
import com.ensa.gi4.PropertiesConfigurer;
import com.ensa.gi4.config.MaterielServiceTestConfig;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertiesConfigurer.class, DatabaseConfig.class, MaterielServiceTestConfig.class})
public class GestionMaterielServiceTest {

	
	@Autowired
	@Qualifier("getLivreService")
	private GestionMaterielService livreService;
	
	@Autowired
	@Qualifier("getChaiseService")
	private GestionMaterielService chaiseService;
	 
	
	private Materiel livre, chaise; 
	
	@Before
	public void setUp() {
		livre = new Livre();
		livre.setName("LIVRE");
		livre.setCode("livre1");
		livre.setStock(5);
		
		chaise = new Chaise();
		chaise.setName("CHAISE");
		chaise.setCode("chaise1");
		chaise.setStock(5);
	}

	
	@Test()
	public void testAddMateriel() {
		assertEquals(1, livreService.ajouterNouveauMateriel(livre));
		assertEquals(1, chaiseService.ajouterNouveauMateriel(chaise));
	
		// verification de l'opération au niveau de la base de donnée si bésoins
		/* TimeUnit time = TimeUnit.MINUTES; 
		time.sleep(2);*/
	}
	
	@Test
	public void testUpdateMateriel() {	
		assertEquals(1, livreService.modifierMateriel("livre1", 10, "livre1"));
		assertEquals(1, chaiseService.modifierMateriel("chaise1", 10, "chaise1"));
	}
	
	@Test
	public void testMaterielIndisponible() {	
		assertEquals(1, livreService.materielIndisponible("livre1"));
		assertEquals(1, chaiseService.materielIndisponible("chaise1"));
	}
			
	@Test
	public void testDeleteMateriel() {
		assertEquals(1, livreService.supprimerMateriel("livre1"));
		assertEquals(1, chaiseService.supprimerMateriel("chaise1"));
	}
	
	
	
	

}
