package com.ensa.gi4.datatabase.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.User;

@Repository
public class UserDaoImpl implements UserDao, InitializingBean {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	@Autowired
	@Qualifier("livreDaoImpl")
	private MaterielDao livreDao;
	@Autowired
	@Qualifier("chaiseDaoImpl")
	private MaterielDao chaiseDao;

	@Override
	public void afterPropertiesSet() throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<User> getUsers() {
		String query = "select * from user";
		List<User> users = jdbcTemplate.query(query, new UserRowMapper());
		return users;
	}

	@Override
	public User getUser(String userName){
		String query = "select * from user where username = ?";
		try {
			return jdbcTemplate.queryForObject(query, new UserRowMapper(), userName);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUser(int userId) {
		String query = "select * from user where id = ?";
		try {
			return jdbcTemplate.queryForObject(query, new UserRowMapper(), userId);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Materiel> getMateriels(String userName) {
		String livreQuery = "select livre_id from user_livre where user_id = ?";
		String chaiseQuery = "select chaise_id from user_chaise where user_id = ?";
		User user = getUser(userName);
		int id = user.getId();
		List<Integer> livreIds = jdbcTemplate.query(livreQuery, new LivreIdRowMapper(), id);
		List<Integer> chaiseIds = jdbcTemplate.query(chaiseQuery, new ChaiseIdRowMapper(), id);
		List<Materiel> materiels = new ArrayList();
		for (int i : livreIds) {
			materiels.add(livreDao.findOne(i));
		}
		for (int i : chaiseIds) {
			materiels.add(chaiseDao.findOne(i));
		}
		return materiels;
	}

	@Override
	public List<Role> getRoles(int userId) {
		String query1 = "select role_id from user_role where user_id = ?";
		String query2 = "select * from role where id = ?";
		List<Integer> rolesIds = jdbcTemplate.query(query1, new IntRowMapper(), userId);
		List<Role> roles = new ArrayList<Role>();
		for (int i : rolesIds) {
			Role role = jdbcTemplate.queryForObject(query2, new RoleRowMapper(), i);
			roles.add(role);
		}
		return roles;
	}

	public class UserRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			User user = new User();
			user.setId(resultSet.getInt("id"));
			user.setUserName(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			return user;
		}

	}

	public class IntRowMapper implements RowMapper<Integer> {

		@Override
		public Integer mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			int result = resultSet.getInt("role_id");
			return result;
		}

	}

	public class LivreIdRowMapper implements RowMapper<Integer> {

		@Override
		public Integer mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			int result = resultSet.getInt("livre_id");
			return result;
		}

	}

	public class ChaiseIdRowMapper implements RowMapper<Integer> {

		@Override
		public Integer mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			int result = resultSet.getInt("chaise_id");
			return result;
		}

	}

	public class RoleRowMapper implements RowMapper<Role> {

		@Override
		public Role mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			Role role = new Role();
			role.setId(resultSet.getInt("id"));
			role.setName(resultSet.getString("name"));
			return role;
		}

	}

}
