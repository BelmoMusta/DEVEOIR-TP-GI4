package com.ensa.gi4;

import com.ensa.gi4.controller.GestionMaterielController;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionPersonneService;
import com.ensa.gi4.service.impl.GestionChaiseServiceImpl;
import com.ensa.gi4.service.impl.GestionLivreServiceImpl;
import com.ensa.gi4.service.impl.GestionPersonneServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfig {

    @Bean
    public GestionMaterielService livreServiceBean() {
        return new GestionLivreServiceImpl();
    }

    @Bean
    public GestionMaterielService ChaiseServiceBean() {
        return new GestionChaiseServiceImpl();
    }

    @Bean
    @Lazy
    public GestionMaterielController materielControllerBean() {
        return new GestionMaterielController();
    }
}
