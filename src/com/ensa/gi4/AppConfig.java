package com.ensa.gi4;

import com.ensa.gi4.controller.GestionMaterielController;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionUserService;
import com.ensa.gi4.service.impl.GestionUserServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfig {

    /*@Bean
    public GestionUserService UserServiceBean() {
        return new GestionUserServiceImpl();
    }*/

    @Bean
    @Lazy
    public GestionMaterielController materielControllerBean() {
        return new GestionMaterielController();
    }
}
