package com.ensa.gi4.datatabase.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.Materiel;

import javax.sql.DataSource;
import java.util.List;

public abstract class GenericDAO implements InitializingBean {
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Override
	public void afterPropertiesSet() { // from InitializingBean
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	protected void add(String table, Materiel materiel) {
		String query = "INSERT INTO " + table + " (NAME,CODE,STOCK,DISPONIBILITE) VALUES (?,?,?,?)";
		int c = jdbcTemplate.update(query, materiel.getName(), materiel.getCode(), materiel.getStock(),
				materiel.isAvailable());
	}

	protected List<Materiel> findAll(String table) {
		String query = "SELECT * FROM " + table + ";";
		return jdbcTemplate.query(query, getRowMapper());
	}

	protected Materiel findOne(String table, String nom) {
		String query = "SELECT * FROM " + table + " WHERE name = ?";
		return jdbcTemplate.queryForObject(query, getRowMapper(), nom);
	}

	protected Materiel findById(String table, int id) {
		String query = "SELECT * FROM " + table + " WHERE id = ?";
		return jdbcTemplate.queryForObject(query, getRowMapper(), id);
	}

	protected void louerMateriel(String table, String nomMateriel, int userId) {
		Materiel materiel = findOne(table, nomMateriel);
		if (materiel.getStock() >= 1 && materiel.isAvailable()) {
			materiel.setStock(materiel.getStock() - 1);
			update(table, materiel.getId(), materiel);
			String query = "Insert into user_" + table + " (user_id," + table + "_id ) values (?,?)";
			int rows = jdbcTemplate.update(query, userId, materiel.getId());
		} else {
			System.out.println("vous pouvez pas effectuer cette operation");
		}
	}

	protected void rendreMateriel(String table, String nomMateriel, int userId) {
		Materiel materiel = findOne(table, nomMateriel);
		materiel.setStock(materiel.getStock() + 1);
		update(table, materiel.getId(), materiel);
		String query = "delete from user_" + table + " where user_id = ?";
		int rows = jdbcTemplate.update(query, userId);
	}

	protected void markerNonDisponible(String table, String nomMateriel) {
		Materiel materiel = findOne(table, nomMateriel);
		materiel.setAvailable(false);
		update(table, materiel.getId(), materiel);
	}

	public void update(String table, int id, Materiel materiel) {
		String query = "UPDATE " + table + " SET NAME = ?, CODE = ?, STOCK = ?, DISPONIBILITE = ? WHERE ID = ?";
		jdbcTemplate.update(query, materiel.getName(), materiel.getCode(), materiel.getStock(), materiel.isAvailable(),
				id);
	}

	public void delete(String table, int id) {
		String query = "DELETE FROM " + table + " WHERE id = ?";
		String query2 = "DELETE FROM user_" + table + " WHERE " + table + "_id = ?";
		jdbcTemplate.update(query2, id);
		jdbcTemplate.update(query, id);
	}

	protected abstract RowMapper<Materiel> getRowMapper();
}
