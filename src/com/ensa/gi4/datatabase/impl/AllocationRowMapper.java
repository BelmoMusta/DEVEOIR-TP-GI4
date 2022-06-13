/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Allocation;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


public class AllocationRowMapper  implements RowMapper<Allocation>{

    @Override
    public Allocation mapRow(ResultSet rs, int i) throws SQLException {
        
        if(rs!=null)
        {
            Allocation alloc = new Allocation() { // because it is abstract
        };
       int qta = rs.getInt(2);
       boolean rendu = rs.getBoolean(3);
       int idM = rs.getInt(4);
       int idU = rs.getInt(5);
      
      
       alloc.setQta(qta);
       alloc.setRendu(rendu);
       alloc.setIdM(idM);
       alloc.setIdU(idU);
        return alloc;
        }
        
        else 
            return null;  
    }
    
    
}
