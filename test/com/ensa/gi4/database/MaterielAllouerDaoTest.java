package com.ensa.gi4.database;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ensa.gi4.DatabaseConfig;
import com.ensa.gi4.PropertiesConfigurer;
import com.ensa.gi4.config.MaterielAllouerDaoTestConfig;
import com.ensa.gi4.datatabase.api.MaterielAllouerDao;
import com.ensa.gi4.modele.MaterielAllouer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertiesConfigurer.class, DatabaseConfig.class, MaterielAllouerDaoTestConfig.class})
public class MaterielAllouerDaoTest {
	
	@Autowired
	private MaterielAllouerDao materielAllouerDao;
	
	@Test
	public void testListeMaterielAlloueParUser() {
		
		Optional<List<MaterielAllouer>> materielAllouerOptional =  materielAllouerDao.listeMaterielAlloueParUser(); 
		assertNotNull(materielAllouerOptional.get());
	}

}
