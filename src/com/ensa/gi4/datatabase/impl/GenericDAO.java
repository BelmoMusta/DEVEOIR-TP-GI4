package com.ensa.gi4.datatabase.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

	protected void add(String query, Materiel materiel) {
		int c = jdbcTemplate.update(query, materiel.getName(), materiel.getCode(), materiel.getStock(),
				materiel.isAvailable());
	}

	protected List<Materiel> findAll(String query) {
		return jdbcTemplate.query(query, getRowMapper());
	}

	protected Materiel findOne(String query, String nom) {
		try {
			return jdbcTemplate.queryForObject(query, getRowMapper(), nom);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected Materiel findOne(String query, int id) {
		try {
			return jdbcTemplate.queryForObject(query, getRowMapper(), id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected void louerMateriel(String query, Materiel materiel, int userId) {
		if (materiel.getStock() >= 1 && materiel.isAvailable()) {
			materiel.setStock(materiel.getStock() - 1);
			update(query, materiel.getId(), materiel);
			int rows = jdbcTemplate.update(query, userId, materiel.getId());
		} else {
			System.out.println("vous pouvez pas effectuer cette operation");
		}
	}

	protected void rendreMateriel(String query, Materiel materiel, int userId) {
		materiel.setStock(materiel.getStock() + 1);
		update(query, materiel.getId(), materiel);
		int rows = jdbcTemplate.update(query, userId);
	}

	protected void markerNonDisponible( Materiel materiel) {
		materiel.setAvailable(false);
	}

	public void update(String query, int id, Materiel materiel) {
		try {
			jdbcTemplate.update(query, materiel.getName(), materiel.getCode(), materiel.getStock(),
					materiel.isAvailable(), id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(String query1,String query2, int id) {
		try {
			jdbcTemplate.update(query1, id);
			jdbcTemplate.update(query2, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected abstract RowMapper<Materiel> getRowMapper();
}
