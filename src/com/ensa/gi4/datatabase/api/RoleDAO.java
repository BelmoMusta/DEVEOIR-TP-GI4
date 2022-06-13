/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Role;


public interface RoleDAO {
    
     Role findRole(String idR);
}
