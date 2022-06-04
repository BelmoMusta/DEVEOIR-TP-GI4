package com.ensa.gi4.modele;

import java.util.Arrays;


public class Livre extends Materiel {

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



    @Override
    public String toString() {
        return "Chaise {" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", type='" + this.getType() + '\'' +
                ", author='" + this.getAuthor() + '\'' +
                ", allocated=" + this.isAllocated() +
                '}';
    }
}
