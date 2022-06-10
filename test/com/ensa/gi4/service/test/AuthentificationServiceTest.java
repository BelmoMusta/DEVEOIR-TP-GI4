package com.ensa.gi4.service.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
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
import com.ensa.gi4.config.AuthentificationServiceTestConfig;
import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.AuthentificationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertiesConfigurer.class, DatabaseConfig.class, AuthentificationServiceTestConfig.class})
public class AuthentificationServiceTest {
	
	@Autowired
	@Qualifier("getAuthentificationService")
	AuthentificationService authentificationService; 
	
	private List<String> adminData, userData; 
	
	@Before
	public void setUp() {
		adminData = new ArrayList<String>(); 
		adminData.add(0, "admin1");
		adminData.add(1, "1234");
		
		userData  =new ArrayList<String>(); 
		userData.add(0, "user1");
		userData.add(1, "5678");
		
	}
	
	@Test
	public void testLogin() {
		// admin
		Optional<User> adminOptional = authentificationService.login(adminData);
		
		assertEquals("admin1", adminOptional.get().getName());
		assertEquals(Role.ADMIN, adminOptional.get().getRole());

		// user 
		Optional<User> userOptional = authentificationService.login(userData); 
		
		assertEquals("user1", userOptional.get().getName());
		assertEquals(Role.USER, userOptional.get().getRole());
	}

}


