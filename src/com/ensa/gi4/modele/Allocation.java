/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ensa.gi4.modele;

public class Allocation {
    
    private int qta;
    private Boolean rendu;
    private int idU;
    private int idM;
    
    public int getIdM() {
        return idM;
    }

    public int getIdU() {
        return idU;
    }

    public int getQta() {
        return qta;
    }

   

    public void setIdM(int idM) {
        this.idM = idM;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    public void setRendu(Boolean rendu) {
        this.rendu = rendu;
    }
    public boolean getRendu(){
        return rendu;
    }
    
    
}
