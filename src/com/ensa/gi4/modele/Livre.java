package com.ensa.gi4.modele;

public class Livre extends Materiel {
    public Livre() {
        super();
    }

    public Livre(String name,String code,Integer stock) {
        this.name = name;
        this.code=code;
        this.stock=stock;
        if(stock==0){
            this.disponibility=false;
        }else{
            this.disponibility=true;
        }

    }
    public Livre(String name,Integer stock) {
        this.name = name;
        this.stock=stock;
        this.code="LI";
    }

}
