/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import libs.Rok;

/**
 *
 * @author ROR
 */
public class Plan {
    private int id;
    private String nazev;
    private Obor obor;
    private Rok rok;

    public Plan(int id, String nazev, Obor obor, Rok rok) {
        this.id = id;
        this.nazev = nazev;
        this.obor = obor;
        this.rok = rok;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public Obor getObor() {
        return obor;
    }

    public void setObor(Obor obor) {
        this.obor = obor;
    }

    public Rok getRok() {
        return rok;
    }

    public void setRok(Rok rok) {
        this.rok = rok;
    }

    @Override
    public String toString() {
        return nazev + " (" +rok.getRokS()+")";
    }

    
}
