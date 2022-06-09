package com.ensa.gi4;

import com.ensa.gi4.controller.GestionMaterialController;
import com.ensa.gi4.modele.User;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    @Scope("singleton")
    public User authenticatedUser() {
        return new User();
    }

    @Bean
    @Lazy
    public GestionMaterialController materialControllerBean() {
        return new GestionMaterialController();
    }
}


