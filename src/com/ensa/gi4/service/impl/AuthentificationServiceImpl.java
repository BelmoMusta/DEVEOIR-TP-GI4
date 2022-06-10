package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.UserDAO;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.AuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
@Component
public class AuthentificationServiceImpl implements AuthentificationService {
    @Autowired
    public UserDAO userdao;
    public String hach(String pwd)throws NoSuchAlgorithmException{
        MessageDigest msg = MessageDigest.getInstance("MD5");
        byte[] hash = msg.digest(pwd.getBytes(StandardCharsets.UTF_8));
        // convertir bytes en hexadécimal
        String s = "";
        for (byte b : hash) {
            s+=Integer.toString((b & 0xff) + 0x100, 16).substring(1);
        }
        return s;
    }
    //public List<User> users=userdao.findAllUsersDAO();
    @Override
    public User login(String name,String password) throws NoSuchAlgorithmException {
        //boolean exist=false;
        User result=null;
        String pwd =userdao.hach(password);
        System.out.println("hello "+pwd);
        //System
        for(User user:userdao.findAllUsersDAO()){
            //String pwdInDB=user.getPassword();
            //String pwdInDB= userdao.hach(user.getPassword());
            System.out.println("1 "+user.getPassword().equals(pwd));
            if(user.getName().equals(name) && user.getPassword().equals(pwd)){
                System.out.println("2 "+user.getPassword().equals(pwd));
                result=user;
            }
        }
        return result;
        /*User result=null;
        for(User user:userdao.findAllUsersDAO()){
            if(user.getName().equals(name) && user.getPassword().equals(password)){
                result=user;
            }
        }
        return result;*/
    }

    @Override
    public List<User> findAll() {
        return userdao.findAllUsersDAO();
    }

    @Override
    public User findOne(Long id) {
        return userdao.findOneDAO(id);
    }

    @Override
    public boolean signUp(String name, String password) throws NoSuchAlgorithmException {
        return userdao.signUpDAO(name,password);

    }

}
