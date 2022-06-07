package com.ensa.gi4.modele;

public abstract class Materiel {
    private String code;
    private String name;
    private Long alloue;
    
    public Long getAlloue() {
		return alloue;
	}

	public void setAlloue(Long alloue) {
		this.alloue = alloue;
	}

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

    @Override
    public String toString() {
        return "name = " + name + ", code = " + code+" ,alloue = " + alloue +"\n";
    }
}
