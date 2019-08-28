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
public class Obor {
    private int id;
    private String nazev;
    private int odhad;
    private String forma;

    public Obor(int id, String nazev, int odhad, String forma) {
        this.id = id;
        this.nazev = nazev;
        this.odhad = odhad;
        this.forma = forma;
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

    public int getOdhad() {
        return odhad;
    }

    public void setOdhad(int odhad) {
        this.odhad = odhad;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }
    
    public int getFormaId(){
        if (this.forma.equals("Prezenční")) {
            return 1;
        }else if(this.forma.equals("Dálková")){
            return 2;
        }else{
            return 3;
        }
    }

    @Override
    public String toString() {
        return nazev;
    }
}
