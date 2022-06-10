/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.User;
import java.util.List;


public interface UserDAO {
    
    List<User> findAll();

    User findOne(String id);
    
     User findId(String name);
    
    User findPerson(String username,String password) ;
    
    User Authentification();
    
    
}
