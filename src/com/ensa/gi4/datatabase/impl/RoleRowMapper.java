/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class RoleRowMapper implements RowMapper<Role>{

    @Override
    public Role mapRow(ResultSet rs, int i) throws SQLException {
        Role role = new Role() { // because it is abstract
        };
        String name = rs.getString(2);
        role.setName(name);
        return role;
    }
    
    }

