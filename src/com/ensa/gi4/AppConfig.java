package com.ensa.gi4;

import com.ensa.gi4.controller.GestionMaterielController;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.datatabase.impl.MaterielDaoImpl;
import com.ensa.gi4.datatabase.impl.UserDaoImpl;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionUser;
import com.ensa.gi4.service.impl.GestionUserImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfig {

    @Bean
    @Lazy
    public GestionMaterielController materielControllerBean() {
        return new GestionMaterielController();
    }

//    @Bean
//    public UserDao userDaoBean() {
//        return new UserDaoImpl();
//    }
//
//    @Bean
//    public GestionUser gestionUserBean() {
//        return new GestionUserImp();
//    }
//
//    @Bean
//    public MaterielDao MaterilDaoBean() {
//        return new MaterielDaoImpl();
//    }


}
