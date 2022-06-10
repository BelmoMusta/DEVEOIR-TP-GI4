package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.UserDAO;
import com.ensa.gi4.modele.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
@Repository
public class UserDAOImpl extends GenericDAO<User> implements UserDAO {
    @Override
    public List<User> findAllUsersDAO() {
        String query="SELECT * FROM USERS";
        return super.findAll(query);
    }

    @Override
    public User findOneDAO(Long id) {
        String query="SELECT * FROM USERS WHERE ID=?";
        return super.findOne(query,id);
    }
    @Override
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
    @Override
    public boolean signUpDAO(String name, String password) throws NoSuchAlgorithmException {
        String query ="INSERT INTO USERS (name,password,role) VALUES(?,?,?)";
        User result=null;
        List<User> list=findAllUsersDAO();
        boolean exist=false;

        String pwd=hach(password);

        for(User user:list){
            if(user.getName().equals(name) && user.getPassword().equals(pwd)){
                exist=true;
                break;
            }
        }
        if(exist==true){
            System.out.println("L'utilisateur "+name+" existe déja,veillez saisir un autre nom et mot de passe!!!");
        }else{
        super.signUpDAO(query,name,pwd,"user");
        System.out.println("Inscription réussite, bienvenu chez nous!!!!");
        }
        return !exist;

    }

    @Override
    protected RowMapper<User> getRowMapper() {
        return new UserRowMapper();
    }
}
