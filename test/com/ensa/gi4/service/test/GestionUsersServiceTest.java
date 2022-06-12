package com.ensa.gi4.service.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.ensa.gi4.service.impl.GestionUsersServiceImpl;

import junit.framework.TestCase;

public class GestionUsersServiceTest extends TestCase {

	@Autowired
	GestionUsersServiceImpl gusi;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		gusi = new GestionUsersServiceImpl();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void testGestionMateriel() {
		

		assertEquals(gusi.listUsers().size(),2);
		

	}
	

}
