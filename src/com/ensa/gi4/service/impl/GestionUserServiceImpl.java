package com.ensa.gi4.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionUserService;

@Service
public class GestionUserServiceImpl implements GestionUserService {

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> getUsers() {
		List<User> users = userDao.getUsers();
		return users;
	}

	@Override
	public User getUser(String userName) {

		User user = userDao.getUser(userName);
		return user;
	}

	@Override
	public List<Materiel> getMateriels(String userName) {
		List<Materiel> materiels = userDao.getMateriels(userName);
		return materiels;
	}

	@Override
	public List<Role> getUseRoles(int id) {

		return userDao.getRoles(id);
	}

	@Override
	public User getUser(int userId) {
		return userDao.getUser(userId);
	}

}
