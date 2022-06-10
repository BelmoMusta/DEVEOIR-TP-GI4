package com.ensa.gi4.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.UserService;

@Component("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
    private UserDao userDao;
	
	@Override
	public Boolean findUser(User user) {
		
		User userInput;
		
		userInput= userDao.findUser(user.getUsername(), user.getPassword());
		if(userInput.getUsername().equals(user.getUsername()) && userInput.getPassword().equals(user.getPassword())) {
			System.out.println("Login successed !!!! \n");
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean findRole(User user, String role) {
		
		return userDao.findRole(user.getUsername()).equals(role);
		
	}

	public User findUserInfo(String username,String password) {
		return userDao.findUser(username,password);
	}
}
