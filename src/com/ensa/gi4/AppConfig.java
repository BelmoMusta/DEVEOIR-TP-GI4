package com.ensa.gi4;

import com.ensa.gi4.controller.AuthenticationController;
import com.ensa.gi4.controller.GestionMaterielController;
import com.ensa.gi4.service.api.AuthenticationService;
import com.ensa.gi4.service.api.MaterialsManagingService;
import com.ensa.gi4.service.impl.AuthenticationServiceImpl;
import com.ensa.gi4.service.impl.MaterialsManagingServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfig {

    @Bean
    public MaterialsManagingService materialsManagingService() {
        return new MaterialsManagingServiceImpl();
    }
    @Bean
    public AuthenticationService authenticationService() {
        return new AuthenticationServiceImpl();
    }
    @Bean
    public AuthenticationController authenticationController() {
        return new AuthenticationController();
    }

//    @Bean
//    public ApplicationPublisher applicationPublisherBean() { return new ApplicationPublisher();}

    @Bean
    @Lazy
    public GestionMaterielController materielControllerBean() {
        return new GestionMaterielController();
    }
}
