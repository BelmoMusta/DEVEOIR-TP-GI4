/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.RoleDAO;
import com.ensa.gi4.modele.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl extends GenericDAO<Role> implements RoleDAO{

    @Override
    protected RowMapper<Role> getRowMapper() {
        return new RoleRowMapper();
    }

    @Override
    public Role findRole(String idR) {
        return super.findOne("SELECT * FROM roles WHERE idR=?;", idR);
    }
    
}
