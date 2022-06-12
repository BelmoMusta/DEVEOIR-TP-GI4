package com.ensa.gi4.service.test;



import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.impl.GestionMaterielServiceImpl;
public class GestionMaterielServiceTest {
	
	private GestionMaterielService gestionMaterielService;
	@Before 
	public void setUp() {
		gestionMaterielService = new GestionMaterielServiceImpl();
	}
	
	@After
	public void tearDown() {
		gestionMaterielService = null;
	}
	
	@Test
public void TestFunction() {
		assertEquals(1,1);
	
}
}
