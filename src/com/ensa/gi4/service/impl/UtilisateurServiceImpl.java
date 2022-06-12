package com.ensa.gi4.service.impl;


import com.ensa.gi4.datatabase.api.UtilisateurDao;
import com.ensa.gi4.modele.Utilisateur;
import com.ensa.gi4.service.api.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.annotation.PostConstruct;
import java.util.List;

@Component("UtilisateurService")
public class UtilisateurServiceImpl implements UtilisateurService {


    @Autowired
    UtilisateurDao utilisateurDao;

    @PostConstruct
    public void init(){
        List<Utilisateur> users = utilisateurDao.findAllUsers();
        String hashedPassword;
        for(int i = 0; i<users.size(); i++){
             users.get(i).setPassword(BCrypt.hashpw(users.get(i).getPassword(), BCrypt.gensalt(10))) ;
            hashedPassword = users.get(i).getPassword();
                    utilisateurDao.hashPassword(users.get(i));
        }


    }
    @Override
    public Utilisateur chercherUtilisateur(String username, String password) {
        Utilisateur user = null;
       // System.out.println(utilisateurDao.findUser(username, password));
        try {

            user =utilisateurDao.findUser(username);
            if(!BCrypt.checkpw(password, user.getPassword())){
                System.out.println("mot de passe incorrecte");
                return null;
            } else {

                System.out.println(utilisateurDao.findUser(username));
            }
           //System.out.println(utilisateurDao.findUser(username, password).getRole());
            //return user;
        } catch (Exception e) {

            System.out.println("Mot de passe ou nom d'utlisateur incorrecte");
        }


        return user;
    }
}
