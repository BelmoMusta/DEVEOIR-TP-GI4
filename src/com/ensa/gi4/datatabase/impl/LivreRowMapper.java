package com.ensa.gi4.datatabase.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.TreePath;

import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;

public class LivreRowMapper implements RowMapper<Materiel>{

	@Override
	public Materiel mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Livre livre=new Livre();
		livre.setId(rs.getInt("id"));
		livre.setCode(rs.getString("CODE"));
		livre.setName(rs.getString("NAME"));
		livre.setStock(rs.getInt("stock"));
		livre.setAvailable(rs.getBoolean("disponibilite"));
		return livre;
	}

	

}
