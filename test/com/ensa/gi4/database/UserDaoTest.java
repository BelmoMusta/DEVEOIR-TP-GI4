package com.ensa.gi4.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
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
import com.ensa.gi4.config.UserDaoTestConfig;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertiesConfigurer.class, DatabaseConfig.class, UserDaoTestConfig.class})
public class UserDaoTest {
	
	@Autowired
	private UserDao userDao; 
	private User admin, user; 
	
	@Before
	public void setUp() {
		admin = new User();
		admin.setName("admin1");
		admin.setRole(Role.ADMIN);
		
		user = new User(); 
		user.setName("user1");
		user.setRole(Role.USER);
	}
	
	@Test
	public void testlogin() {
		
		//admin
		
		List<String> adminData = new ArrayList<String>(); 
		adminData.add(0,"admin1");
		adminData.add(1,"1234"); 
		
		Optional<User> adminOptional = userDao.login(adminData); 
		
		assertEquals("admin1", adminOptional.get().getName());
		assertEquals(Role.ADMIN, adminOptional.get().getRole());
		
		// user
		List<String> userData = new ArrayList<String>(); 
		userData.add(0,"user1");
		userData.add(1,"5678"); 
		
		Optional<User> userOptional = userDao.login(userData); 
		
		assertEquals("user1", userOptional.get().getName());
		assertEquals(Role.USER, userOptional.get().getRole());
	
	}
	
	@Test
	public void testFindAllUser() {
		
		Optional<List<User>> listUserOptional = userDao.findAll(); 
		
		assertNotNull(listUserOptional.get());
	}
	

}
