package com.ensa.gi4;

import com.ensa.gi4.controller.GestionMaterielController;
import com.ensa.gi4.listeners.ApplicationPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;


@Configuration
/*
@PropertySources({
    @PropertySource("classpath:messages.properties"),
    @PropertySource("classpath:application.properties")
})*/
@ComponentScan("com.ensa.gi4")
public class AppConfig {
	
    @Bean(name="controllerPricipal")
    public GestionMaterielController materielControllerBean() {
        return new GestionMaterielController();
    }
   
  
    @Bean
    public  ApplicationPublisher publisher() {
        return new ApplicationPublisher();
    }
    /*
    @Bean
    public PasswordEncoder encoder() {    
        return new BCryptPasswordEncoder();

    }
    */
    @Bean
    public ResourceBundleMessageSource messageSource() {

    	ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("resources/messages");
        source.setUseCodeAsDefaultMessage(true);

        return source;
    }
    
}
