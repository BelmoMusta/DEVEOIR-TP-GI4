package com.ensa.gi4.datatabase.api;
import com.ensa.gi4.modele.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserDAO {
    List<User> findAllUsersDAO();
    User findOneDAO(Long id);
    boolean signUpDAO(String name,String password) throws NoSuchAlgorithmException;
     String hach(String pwd)throws NoSuchAlgorithmException;
    
}
