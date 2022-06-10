package com.ensa.dao;

import com.ensa.modele.Livre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LivreRowMapper implements RowMapper<Livre> {
    @Override
    public Livre mapRow(ResultSet resultSet, int i) throws SQLException {
        Livre livre = new Livre();


        String name_ = resultSet.getString("NAME");
        String code_ = resultSet.getString("CODE");
        String auteur = resultSet.getString("AUTEUR");
        Float prix = resultSet.getFloat("PRIX");

        String code = resultSet.getString(3);
        livre.setCode(code_);
        livre.setName(name_);
        livre.setAuteur(auteur);
        livre.setPrix(prix);

        return livre;
    }
}
