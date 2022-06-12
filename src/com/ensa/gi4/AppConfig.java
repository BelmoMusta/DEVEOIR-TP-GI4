package com.ensa.gi4;

import com.ensa.gi4.controller.GestionMaterielController;
import com.ensa.gi4.controller.SignInController;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.impl.MaterielDaoImpl;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfig {

    //TODO :KEEP IN MIND CURRENT USER
    @Bean
    public User currentUser(){return new User();}

    @Lazy
    @Bean
    @Value("signInController")
    public SignInController signInController(){
        return new SignInController();
    }

  /*  @Lazy
    @Bean
    @Value("materielDao")
    public MaterielDao materielDao(){return new MaterielDaoImpl();
    }*/

    @Bean
    @Lazy
    public GestionMaterielController gestionMaterielController() {
        return new GestionMaterielController();
    }
}
