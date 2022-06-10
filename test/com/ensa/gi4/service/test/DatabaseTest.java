package com.ensa.gi4.service.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@Profile("test")
public class DatabaseTest {

    @Value("${jdbc.test.url}")
    private String urlTest;

    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.driver}")
    private String driverClassName;

    @Value("${jbdc.init.schema}")
    private String initSchema;

    @Value("${jbdc.populate.schema}")
    private String populateSchema;

    @Bean
    @Primary
    public DataSource dataSourceTest() {
        System.out.println("********** TEST DATABASE **********");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(urlTest);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        executeScript(initSchema, dataSource);
        executeScript(populateSchema, dataSource);
        //  lancerH2Console(); // si vous voulez lancer la console H2 après la création du datasource
        return dataSource;
    }

    public static void executeScript(String scriptPath, DataSource dataSource) {
        final Resource pathResource = new ClassPathResource(scriptPath);
        if (pathResource.exists()) {
            DatabasePopulator databasePopulator = new ResourceDatabasePopulator(pathResource);
            DatabasePopulatorUtils.execute(databasePopulator, dataSource);
        } else {
            System.out.println("ERROR : script not found in '" + scriptPath + "'");
        }
    }

}
