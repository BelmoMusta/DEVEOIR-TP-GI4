package com.ensa.gi4.utils;

import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.I18nService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntityUtils {

    private final I18nService i18nService;
    private final Environment environment;

    public final <T extends Materiel> void populateInputFields(T entity, String[] fields){
        int index = 0;
        String[] tmp = entity.getClass().getName().split("\\.");
        String className = tmp[tmp.length-1].toLowerCase();
        List<String> elements = Arrays.asList(environment.getProperty("materiel." + className +".input.fields").split(","));

        Field[] entityFields = Materiel.class.getDeclaredFields();
        for( Field field : entityFields){
            field.setAccessible(true);
            if (elements.contains(field.getName())){
                try {
                    field.set(entity, fields[index]);
                    index++;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public final <T extends Materiel> String[] extractInputFields(T entity){
        List<String> args = new ArrayList<>();
        String[] tmp = entity.getClass().getName().split("\\.");
        String className = tmp[tmp.length-1].toLowerCase();
        List<String> elements = Arrays.asList(environment.getProperty("materiel." + className +".input.fields").split(","));

        Field[] entityFields = Materiel.class.getDeclaredFields();
        for( Field field : entityFields){
            field.setAccessible(true);
            if (elements.contains(field.getName())){
                try {
                    args.add(field.get(entity).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        return args.toArray(String[]::new);
    }

    public final <T extends Materiel> String[] extractFromEntity(T entity, Predicate<Object> predicate){
        List<String> args = new ArrayList<>();
        Field[] fields = Materiel.class.getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            try {
                if (predicate!= null && !predicate.test(field.get(entity))) continue;
                if(field.getType().toString().equals("boolean"))
                    args.add(field.getBoolean(entity)?i18nService.getText("words.yes"):i18nService.getText("words.no"));
                else if (field.get(entity) == null)
                    args.add("-");
                else
                    args.add(field.get(entity).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return args.toArray(String[]::new);
    }

    public final <T extends Materiel> void populateFromResultSet(T materiel,ResultSet rs) {
        String[] columns = environment.getProperty("materiel.fields").split(",");
        Field[] fields = Materiel.class.getDeclaredFields();
        if (columns.length != fields.length){
            throw new MaterielConfigurationError();
        }
        for(int i=0; i<columns.length; i++){
            Field field = fields[i];
            field.setAccessible(true);
            try {
                field.set(materiel, rs.getObject(columns[i]));
            } catch (IllegalAccessException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

class MaterielConfigurationError extends RuntimeException{
    public MaterielConfigurationError() {
        super("Configuration of Materiel Entity is falsy !");
    }
}