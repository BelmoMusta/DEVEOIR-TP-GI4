package com.ensa.gi4.modele;

import com.ensa.gi4.datatabase.api.LivreDao;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Getter @Setter
public abstract class Materiel {


    protected Integer id;
    protected String name;
    protected Integer type;
    protected Integer stock;
    protected Integer allocated;

    @Override
    public String toString() {
        return "Materiel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + id +
                ", stock=" + stock +
                ", allocated=" + allocated +
                '}';
    }

}
