package com.ensa.gi4.modele;

import java.sql.Timestamp;

public abstract class Materiel {
    private String code;
    private String name;
    private Integer stock; 
    private Timestamp timestamp; 

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
	
	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
    public String toString() {
		 
		String value = "name = " + name + ", code = " + code; 
		
		if (timestamp != null) {
			value += ", alloué le : " + timestamp; 
		}
		else {
			value += ", stock = " + stock;
		}
		
        return value; 
    }
}
