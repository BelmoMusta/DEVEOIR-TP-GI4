package com.ensa.gi4.modele;

import org.springframework.stereotype.Component;

@Component
public class Materiel {
    public Materiel() {
	}


	protected int matId;
    protected String name;
    protected boolean alloue;
    protected String type;
	protected int userId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMatId() {
        return matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

   

	public boolean getAlloue() {
		return alloue;
	}

	public void setAlloue(boolean alloue) {
		this.alloue = alloue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	} 
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
    public String toString() {
		   return "\nnom:" +name+", alloue= "+alloue+", type="+type+"\n";
    }


	
		
	
}	
