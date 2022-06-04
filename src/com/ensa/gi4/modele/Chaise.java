package com.ensa.gi4.modele;

public class Chaise extends Materiel {

    public String getWood() {
        return this.wood;
    }

    public void setWood(String wood) {
        this.wood = wood;
    }


    @Override
    public String toString() {
        return "Chaise {" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", type='" + this.getType() + '\'' +
                ", wood='" + this.wood + '\'' +
                ", allocated=" + this.isAllocated() +
                '}';
    }

}
