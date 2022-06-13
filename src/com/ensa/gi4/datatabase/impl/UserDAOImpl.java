/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.UserDAO;
import com.ensa.gi4.modele.User;
import java.util.List;
import java.util.Scanner;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends GenericDAO<User> implements UserDAO {

    @Override
    public List<User> findAll() {
        return super.findAll("SELECT * FROM users;");
    }

    @Override
    public User findOne(String id) {
        return super.findOne("SELECT * FROM users WHERE idU=?;", id);
    }

    @Override
    protected RowMapper<User> getRowMapper() {
        return new UserRowMapper();
    }

    @Override
    public User findPerson(String username, String password) {
        return super.findEntity("SELECT * FROM users WHERE username=? and password=?;", username,password);
    }

   //Verifier si l'utilisateur existe
    @Override
    public User Authentification()
    {
        boolean find = true;
        String username = null;
        String password = null;

        while(find)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Your username : ");
            username = scanner.next();
            scanner.nextLine();
            System.out.println("Your password : ");
            password = scanner.next(); 

            try{
                User u = findPerson(username,password);
                find=false;
                return u;
                
            }
            catch(Exception e){
                System.out.println("user not found ");
                
            }
        }
        return null;
    }

    @Override
    public User findId(String name) {
        return super.findOne("SELECT * FROM users WHERE username=?", name);
    }


}
