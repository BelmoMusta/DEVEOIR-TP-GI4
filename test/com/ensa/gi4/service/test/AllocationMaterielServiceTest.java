package com.ensa.gi4.service.test;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ensa.gi4.DatabaseConfig;
import com.ensa.gi4.PropertiesConfigurer;
import com.ensa.gi4.config.AllocationMaterielServiceTestConfig;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.TypeMateriel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.AllocationMaterielService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertiesConfigurer.class, DatabaseConfig.class, AllocationMaterielServiceTestConfig.class})
public class AllocationMaterielServiceTest {
	
	@Autowired
	MaterielDao materielDao; 
	@Autowired
	@Qualifier("getAllocationMaterielService")
	AllocationMaterielService allocationMaterielService;
	
	private Materiel livre, chaise; 
	private User user, admin; 
	
	@Before
	public void setUp() {
		// livre
		livre = new Livre(); 
		livre.setName(TypeMateriel.LIVRE.toString());
		livre.setCode("livre1");
		livre.setStock(10);
		
		// chaise
		chaise = new Chaise(); 
		chaise.setName(TypeMateriel.CHAISE.toString());
		chaise.setCode("chaise1");
		chaise.setStock(10);
		
		materielDao.ajouter(livre);
		materielDao.ajouter(chaise);
		
		user = new User();
		user.setName("user1");
		user.setRole(Role.USER);
		
		admin = new User(); 
		admin.setName("admin1");
		admin.setRole(Role.ADMIN);
		
	}
	
	
	@Test
	public void testRechercheEtAllocationMateriel() {
		
		// operation  livre
		
		// recherche
		Optional<Materiel> livreOptional = allocationMaterielService.chercherMateriel(TypeMateriel.LIVRE, "livre1"); 
		
		assertEquals(TypeMateriel.LIVRE.toString(), livreOptional.get().getName());
		assertEquals("livre1", livreOptional.get().getCode());
		assertEquals((Integer) 10, livreOptional.get().getStock());
		
		//allocation
		assertEquals(1, allocationMaterielService.allouerMateriel(TypeMateriel.LIVRE, "livre1", user));
		assertEquals(1, allocationMaterielService.allouerMateriel(TypeMateriel.LIVRE, "livre1", admin));
	
		
		// rendre Allocation 
		assertEquals(1, allocationMaterielService.rendreMaterielAlloue(TypeMateriel.LIVRE, "livre1", user));
		assertEquals(1, allocationMaterielService.rendreMaterielAlloue(TypeMateriel.LIVRE, "livre1", admin));
		

		//operation chaise
		
		// recherche
		Optional<Materiel> chaiseOptional = allocationMaterielService.chercherMateriel(TypeMateriel.CHAISE, "chaise1"); 
		
		assertEquals(TypeMateriel.CHAISE.toString(), chaiseOptional.get().getName());
		assertEquals("chaise1", chaiseOptional.get().getCode());
		assertEquals((Integer) 10, chaiseOptional.get().getStock());
		
		
		//allocation
		assertEquals(1, allocationMaterielService.allouerMateriel(TypeMateriel.CHAISE, "chaise1", user));
		assertEquals(1, allocationMaterielService.allouerMateriel(TypeMateriel.CHAISE, "chaise1", admin));
		
		// rendre Allocation 
		assertEquals(1, allocationMaterielService.rendreMaterielAlloue(TypeMateriel.CHAISE, "chaise1", user));
		assertEquals(1, allocationMaterielService.rendreMaterielAlloue(TypeMateriel.CHAISE, "chaise1", admin));
		
	}
	
	

}
