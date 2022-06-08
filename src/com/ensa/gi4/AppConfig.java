package com.ensa.gi4;

import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages/label");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

}
