package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.UtilisateurDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UtilisateurDaoImpl extends GenericDAO<Utilisateur> implements UtilisateurDao {


    @Override
    protected RowMapper getRowMapper() {
        return new UtilisateurRowMapper();
    }

    @Override
    public List<Utilisateur> findAllUsers() {
        return super.findAll("Select * FROM utilisateur");
    }

    @Override
    public int hashPassword(Utilisateur utilisateur) {
        return super.updatePassword("UPDATE Utilisateur set password = ? where idUser=?", utilisateur);
    }

    @Override
    public Utilisateur findUser(String username) {
        return super.findUtilisateur("SELECT * FROM utilisateur WHERE username=?", username);
    }
}
