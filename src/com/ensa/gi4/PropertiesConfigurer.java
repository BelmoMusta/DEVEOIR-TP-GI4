package com.ensa.gi4;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropertiesConfigurer {

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        final List<Resource> resourceList = new ArrayList<Resource>();

        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        resourceList.add(new ClassPathResource("resources/application.properties"));
        resourceList.add(new ClassPathResource("resources/messages.properties"));
        propertyPlaceholderConfigurer.setLocations(resourceList.toArray(new Resource[]{}));
     //   propertyPlaceholderConfigurer.setLocation(new ClassPathResource("resources/application.properties"));
        return propertyPlaceholderConfigurer;
    }

}