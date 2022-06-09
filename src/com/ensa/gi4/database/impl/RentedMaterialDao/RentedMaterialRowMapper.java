package com.ensa.gi4.database.impl.RentedMaterialDao;

import com.ensa.gi4.modele.RentedMaterial;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RentedMaterialRowMapper implements RowMapper<RentedMaterial> {

    @Override
    public RentedMaterial mapRow(ResultSet resultSet, int i) throws SQLException {
        RentedMaterial rentedMaterial = new RentedMaterial();
        Long id = resultSet.getLong("id");
        Long userid = resultSet.getLong("user_id");
        Long materialId = resultSet.getLong("material_id");
        String deadlineString = resultSet.getString("deadline");
        LocalDate deadline = LocalDate.parse(deadlineString);
        rentedMaterial.setId(id);
        rentedMaterial.setUserId(userid);
        rentedMaterial.getMaterial().setId(materialId);
        rentedMaterial.setDeadline(deadline);
        return rentedMaterial;
    }
}
