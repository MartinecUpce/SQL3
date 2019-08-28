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
public class Mistnost {
    private int id;
    private int kapacita;
    private String oznaceni;

    public Mistnost(int id, int kapacita, String oznaceni) {
        this.id = id;
        this.kapacita = kapacita;
        this.oznaceni = oznaceni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKapacita() {
        return kapacita;
    }

    public void setKapacita(int kapacita) {
        this.kapacita = kapacita;
    }

    public String getOznaceni() {
        return oznaceni;
    }

    public void setOznaceni(String oznaceni) {
        this.oznaceni = oznaceni;
    }

    @Override
    public String toString() {
        return oznaceni;
    }
}
