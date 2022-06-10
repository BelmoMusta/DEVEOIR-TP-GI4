package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.MaterielAllocated;
import com.ensa.gi4.modele.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterielAllocatedRowMapper implements RowMapper<MaterielAllocated> {

    @Override
    public MaterielAllocated mapRow(ResultSet resultSet, int i) throws SQLException {
        MaterielAllocated materielAllocated = new MaterielAllocated() ;

        int id = resultSet.getInt(1);
        int userID = resultSet.getInt(2);
        int materielID = resultSet.getInt(3);
        int quantity = resultSet.getInt(4);
        materielAllocated.setId(id);
        materielAllocated.setUserID(userID);
        materielAllocated.setMaterielID(materielID);
        materielAllocated.setQuantity(quantity);

        return materielAllocated;
    }
}
