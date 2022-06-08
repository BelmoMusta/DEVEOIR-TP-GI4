package com.ensa.gi4;

import com.ensa.gi4.controller.GestionMaterielController;
import com.ensa.gi4.modele.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("singleton")
    public User userCurrent(){
        return new User();
    }


    @Bean
    @Lazy
    public GestionMaterielController materielControllerBean() {
        return new GestionMaterielController();
    }
}
