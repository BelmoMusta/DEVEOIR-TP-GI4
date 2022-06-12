package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurRowMapper implements RowMapper<Utilisateur> {
    @Override
    public Utilisateur mapRow(ResultSet resultSet, int i) throws SQLException {
        Utilisateur utilisateur = new Utilisateur();

        int idUser = resultSet.getInt(1);
        String username = resultSet.getString(2);
        String password = resultSet.getString(3);
        String role = resultSet.getString(4);
        utilisateur.setIdUser(idUser);
        utilisateur.setUsername(username);
        utilisateur.setPassword(password);
        utilisateur.setRole(role);

        return  utilisateur;


    }
}
