package com.ensa.gi4;

import com.ensa.gi4.controller.GestionMaterielController;
import com.ensa.gi4.controller.UserController;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.impl.GestionLivreServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class AppConfig {

    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages/label");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }
}
