package com.ensa.gi4.modele;



public class Livre extends Materiel {

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    @Override
    public String toString() {
        return "Chaise {" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", type='" + this.getType() + '\'' +
                ", author='" + this.getAuthor() + '\'' +
                ", stock=" + this.getStock() +
                '}';
    }
}
