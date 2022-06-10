package com.ensa.dao;

import com.ensa.modele.Chaise;
import com.ensa.modele.Livre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChaiseRowMapper implements RowMapper<Chaise> {
    @Override
    public Chaise mapRow(ResultSet resultSet, int i) throws SQLException {
        Chaise chaise = new Chaise();

        String name = resultSet.getString("name");


        Float hauteur = resultSet.getFloat("HAUTEUR");

        String code = resultSet.getString("code");
        Float prix = resultSet.getFloat("prix");
        chaise.setCode(code);
        chaise.setName(name);
        chaise.setPrix(prix);
        chaise.setHauteur(hauteur);

        return chaise;
    }
}



