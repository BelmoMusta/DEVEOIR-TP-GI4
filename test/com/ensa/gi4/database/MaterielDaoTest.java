package com.ensa.gi4.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ensa.gi4.DatabaseConfig;
import com.ensa.gi4.PropertiesConfigurer;
import com.ensa.gi4.config.MaterielDaoTestConfig;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.TypeMateriel;
import com.ensa.gi4.modele.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertiesConfigurer.class, DatabaseConfig.class, MaterielDaoTestConfig.class})
public class MaterielDaoTest {
	
	@Autowired
	private MaterielDao materielDao; 
	private Materiel livre, chaise; 
	private User user, admin; 
	
	@Before
	public void setUp() {
		
		livre = new Livre();
		livre.setCode("livre1");
		livre.setName(TypeMateriel.LIVRE.toString());
		livre.setStock(5);
		
		chaise = new Chaise();
		chaise.setCode("chaise1");
		chaise.setName(TypeMateriel.CHAISE.toString());
		chaise.setStock(5);
		
		admin = new User(); 
		admin.setName("admin1");
		admin.setRole(Role.ADMIN);
		
		user = new User(); 
		user.setName("user1");
		user.setRole(Role.USER);
	}
	
	@Test
	public void testAddMateriel() {
		
		assertEquals(1, materielDao.ajouter(livre));
		assertEquals(1, materielDao.ajouter(chaise));
	}
	
	
	@Test
	public void testUpdateMateriel() {
		assertEquals(1, materielDao.updateMateriel("livre1", 25, "livre1"));
		assertEquals(1, materielDao.updateMateriel("chaise1", 25, "chaise1"));
	}
	
	@Test
	public void testMaterielIndisponible() {	
		assertEquals(1, materielDao.materielIndisponible("livre1"));
		assertEquals(1, materielDao.materielIndisponible("chaise1"));
	}
			
	
	
	@Test
	public void testAllouerMateriel() {
		assertEquals(1, materielDao.allouerMateriel("livre1", TypeMateriel.LIVRE.toString(), admin));
		assertEquals(1, materielDao.allouerMateriel("livre1", TypeMateriel.LIVRE.toString(), user));
	}
	
	@Test
	public void testRendreMateriel() {
		assertEquals(1, materielDao.rendreMateriel("livre1", TypeMateriel.LIVRE.toString(), admin));
		assertEquals(1, materielDao.rendreMateriel("livre1", TypeMateriel.LIVRE.toString(), user));
	}
	
	@Test
	public void testChercherMateriel() {
		
		Optional<Materiel> livreOptional = materielDao.findOne("livre1", TypeMateriel.LIVRE.toString());  
		Optional<Materiel> chaiseOptional = materielDao.findOne("chaise1", TypeMateriel.CHAISE.toString());  
		
		assertEquals(TypeMateriel.LIVRE.toString(), livreOptional.get().getName());
		assertEquals("livre1", livreOptional.get().getCode());
		assertEquals((Integer) 5, livreOptional.get().getStock());
		
		assertEquals(TypeMateriel.CHAISE.toString(), chaiseOptional.get().getName());
		assertEquals("chaise1", chaiseOptional.get().getCode());
		assertEquals((Integer) 5, chaiseOptional.get().getStock());

	}
	
	@Test
	public void testListeMaterielAllouer() {
		
		Optional<List<Materiel>> listMaterielAdminMaterielOptional = materielDao.listeMaterielAlloue(admin); 
		Optional<List<Materiel>> listMaterielUserMaterielOptional = materielDao.listeMaterielAlloue(user); 
		
		assertNotNull(listMaterielAdminMaterielOptional.get());
		assertNotNull(listMaterielUserMaterielOptional.get());
	}
	
	@Test
	public void testListeMateriel() {
		
		Optional<List<Materiel>> listMaterielOptional = materielDao.findAll(); 
		
		assertNotNull(listMaterielOptional.get());
	}
	
	@Test
	public void testDeleteMateriel() {
		assertEquals(1, materielDao.supprimerMateriel("livre1"));
		assertEquals(1, materielDao.supprimerMateriel("chaise1"));
	}

}
