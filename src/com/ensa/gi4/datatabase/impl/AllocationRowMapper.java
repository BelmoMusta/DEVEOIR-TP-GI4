package com.ensa.gi4.datatabase.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.Allocation;

public class AllocationRowMapper implements RowMapper<Allocation> {
	@Override
    public Allocation mapRow(ResultSet resultSet, int i) throws SQLException {
    	    	
		Allocation allocation = new Allocation() { // because it is abstract
        };
        
        int id = resultSet.getInt(1);
        String userName = resultSet.getString(2);
        String role = resultSet.getString(3);
        String materielName = resultSet.getString(4);
        String type = resultSet.getString(5);
        Date dateDebut = resultSet.getDate(6);
        Date dateFin = resultSet.getDate(7);

        allocation.setId(id);
        allocation.setUserName(userName);;
        allocation.setRole(role);
        allocation.setMaterielName(materielName);
        allocation.setType(type);
        allocation.setDateDebut(dateDebut);;
        allocation.setDateFin(dateFin);;

    	return allocation;
    }
}
