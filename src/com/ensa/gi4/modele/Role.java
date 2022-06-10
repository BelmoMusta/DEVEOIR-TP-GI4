package com.ensa.gi4.modele;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Role {
    private Integer id;
    private String name;


    @Override
    public String toString() {
        return name;
    }
}
