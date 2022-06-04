package com.ensa.gi4.modele;

public abstract class Materiel {
    private String code;
    private String name;
    private Integer stock; 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Override
    public String toString() {
        return "name = " + name + ", code = " + code + ", stock = " + stock;
    }
}
