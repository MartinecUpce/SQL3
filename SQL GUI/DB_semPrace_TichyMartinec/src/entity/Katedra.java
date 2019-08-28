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
public class Katedra {
    private int id;
    private String nazev;
    private String zkratka;
    private Fakulta fakulta;

    public Katedra(int id, String nazev, String zkratka, Fakulta fakulta) {
        this.id = id;
        this.nazev = nazev;
        this.zkratka = zkratka;
        this.fakulta = fakulta;
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

    public Fakulta getFakulta() {
        return fakulta;
    }

    public void setFakulta(Fakulta fakulta) {
        this.fakulta = fakulta;
    }

    @Override
    public String toString() {
        return zkratka;
    }
    
    
}
