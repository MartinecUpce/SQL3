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
public class RozvrhovaAktivita {
    private int id;
    private String tyden;
    private int zacatek;
    private int konec;
    private int schvaleno;
    private String den;
    private int Kapacita;
    private Predmet predmet;
    private String zpusobVyuky;
    private Vyucujici vyucujici;
    private Mistnost mistnost;

    public RozvrhovaAktivita(int id, String tyden, int zacatek, int konec, int schvaleno, String den, int Kapacita, Predmet predmet, String zpusobVyuky, Vyucujici vyucujici, Mistnost mistnost) {
        this.id = id;
        this.tyden = tyden;
        this.zacatek = zacatek;
        this.konec = konec;
        this.schvaleno = schvaleno;
        this.den = den;
        this.Kapacita = Kapacita;
        this.predmet = predmet;
        this.zpusobVyuky = zpusobVyuky;
        this.vyucujici = vyucujici;
        this.mistnost = mistnost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTyden() {
        return tyden;
    }

    public void setTyden(String tyden) {
        this.tyden = tyden;
    }

    public int getZacatek() {
        return zacatek;
    }

    public void setZacatek(int zacatek) {
        this.zacatek = zacatek;
    }

    public int getKonec() {
        return konec;
    }

    public void setKonec(int konec) {
        this.konec = konec;
    }

    public int getSchvaleno() {
        return schvaleno;
    }

    public void setSchvaleno(int schvaleno) {
        this.schvaleno = schvaleno;
    }

    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }

    public int getKapacita() {
        return Kapacita;
    }

    public void setKapacita(int Kapacita) {
        this.Kapacita = Kapacita;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public String getZpusobVyuky() {
        return zpusobVyuky;
    }

    public void setZpusobVyuky(String zpusobVyuky) {
        this.zpusobVyuky = zpusobVyuky;
    }

    public Vyucujici getVyucujici() {
        return vyucujici;
    }

    public void setVyucujici(Vyucujici vyucujici) {
        this.vyucujici = vyucujici;
    }

    public Mistnost getMistnost() {
        return mistnost;
    }

    public void setMistnost(Mistnost mistnost) {
        this.mistnost = mistnost;
    }
}
