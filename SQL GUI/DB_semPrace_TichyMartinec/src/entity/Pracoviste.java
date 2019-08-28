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
public class Pracoviste {
    private int fakultaId;
    private int katedraId;
    private String fakultaPracoviste;
    private String fakultaZkratka;
    private String katedraPracoviste;
    private String katedraZkratka;

    public Pracoviste(int fakultaID, int katedraID, String fakultaPracoviste, String fakultaZkratka, String katedraPracoviste, String katedraZkratka) {
        this.fakultaId = fakultaID;
        this.katedraId = katedraID;
        this.fakultaPracoviste = fakultaPracoviste;
        this.fakultaZkratka = fakultaZkratka;
        this.katedraPracoviste = katedraPracoviste;
        this.katedraZkratka = katedraZkratka;
    }

    public int getFakultaID() {
        return fakultaId;
    }

    public void setFakultaID(int fakultaID) {
        this.fakultaId = fakultaID;
    }

    public int getKatedraID() {
        return katedraId;
    }

    public void setKatedraID(int katedraID) {
        this.katedraId = katedraID;
    }

    public String getFakultaPracoviste() {
        return fakultaPracoviste;
    }

    public void setFakultaPracoviste(String fakultaPracoviste) {
        this.fakultaPracoviste = fakultaPracoviste;
    }

    public String getFakultaZkratka() {
        return fakultaZkratka;
    }

    public void setFakultaZkratka(String fakultaZkratka) {
        this.fakultaZkratka = fakultaZkratka;
    }

    public String getKatedraPracoviste() {
        return katedraPracoviste;
    }

    public void setKatedraPracoviste(String katedraPracoviste) {
        this.katedraPracoviste = katedraPracoviste;
    }

    public String getKatedraZkratka() {
        return katedraZkratka;
    }

    public void setKatedraZkratka(String katedraZkratka) {
        this.katedraZkratka = katedraZkratka;
    }
    
    
}
