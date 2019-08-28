/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ROR
 */
public class Fakulta {
    private int id;
    private String nazev;
    private String zkratka;

    public Fakulta(int id, String nazev, String zkratka) {
        this.id = id;
        this.nazev = nazev;
        this.zkratka = zkratka;
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

    public String getZkratka() {
        return zkratka;
    }

    public void setZkratka(String zkratka) {
        this.zkratka = zkratka;
    }

    @Override
    public String toString() {
        return nazev;
    } 
}
