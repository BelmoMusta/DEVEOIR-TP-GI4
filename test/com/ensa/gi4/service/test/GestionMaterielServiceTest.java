package com.ensa.gi4.service.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.service.impl.GestionMaterielServiceImpl;

import junit.framework.TestCase;

public class GestionMaterielServiceTest  extends TestCase {
    //  test methods go here
	@Autowired
	GestionMaterielServiceImpl gmsi ;
	
	int sizeInitial;

	public GestionMaterielServiceTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception {
		gmsi = new GestionMaterielServiceImpl();
		sizeInitial=gmsi.toutMateriaux().size();
		Livre livre1 = new Livre();
		livre1.setName("livre test");
		livre1.setCode("LITEST");
		Chaise chaise1 = new Chaise();
		chaise1.setName("chaise test");
		chaise1.setCode("CHTEST");
		
		gmsi.ajouterNouveauMateriel(livre1);
		gmsi.ajouterNouveauMateriel(chaise1);
		
		
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	public void testGestionMateriel() {
		boolean test =gmsi.findOne("CHTEST");

		assertEquals(gmsi.toutMateriaux().size(),sizeInitial+2);
		assertTrue(test);

	}
}

