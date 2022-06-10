package com.ensa.dao;

import com.ensa.modele.Allocation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AllocationRowMapper implements RowMapper<Allocation> {
    @Override
    public Allocation mapRow(ResultSet resultSet, int i) throws SQLException {
        Allocation allo=new Allocation();
        String userid=resultSet.getString("userid");
        String productid=resultSet.getString("productid");
        Date date=resultSet.getDate("allocationdate");

        allo.setUserid(userid);allo.setAllocationdate(date);allo.setProductid(productid);
        return allo;
    }
}
