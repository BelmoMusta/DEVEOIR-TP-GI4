package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.personne;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class personneRowMapper implements RowMapper<personne> {

    @Override
    public personne mapRow(ResultSet resultSet, int i) throws SQLException {
        personne person = new personne() { 
        };
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String password = resultSet.getString(3);
        String role = resultSet.getString(4);

        person.setId(id);
        person.setName(name);
        person.setPassword(password);
        person.setRole(role);
        return person;
    }
}
