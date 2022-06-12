package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.RentedMaterial;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RentedMaterialRowMapper implements RowMapper<RentedMaterial> {
    @Override
    public RentedMaterial mapRow(ResultSet resultSet, int i) throws SQLException {
        RentedMaterial rentedMaterial = new RentedMaterial();
        Long idUser = resultSet.getLong("id_user");
        Long id_materiel =resultSet.getLong("id_materiel");
        rentedMaterial.setIdMateriel(id_materiel);
        rentedMaterial.setIdUser(idUser);
        return rentedMaterial;
    }
}
