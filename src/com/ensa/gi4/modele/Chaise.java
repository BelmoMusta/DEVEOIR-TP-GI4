package com.ensa.gi4.modele;

public class Chaise extends Materiel {

    public Chaise() {
        super();
    }

    public Chaise(Long id, String name,Integer stock) {
        this.id = id;
        this.name = name;
        this.stock=stock;
        this.code="CH";
        if(stock==0){
            this.disponibility=false;
        }else{
            this.disponibility=true;
        }
    }
    public Chaise(String name,Integer stock) {
        this.name = name;
        this.stock=stock;
        this.code="LI";
    }

}
