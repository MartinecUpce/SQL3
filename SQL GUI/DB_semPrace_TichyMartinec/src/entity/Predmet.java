/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author ROR
 */
public class Predmet {
    private int id;
    private String nazev;
    private String zkratka;
    private String semestr;
    private int rocnik;
    private String kategorie;
    private String zakonceni;
    private Plan plan;
    private ArrayList<Vyucujici> vyucujici;

    public Predmet(int id, String nazev, String zkratka, String semestr, int rocnik, String kategorie, String zakonceni, Plan plan, ArrayList<Vyucujici> vyucujici) {
        this.id = id;
        this.nazev = nazev;
        this.zkratka = zkratka;
        this.semestr = semestr;
        this.rocnik = rocnik;
        this.kategorie = kategorie;
        this.zakonceni = zakonceni;
        this.plan = plan;
        this.vyucujici = vyucujici;
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

    public String getSemestr() {
        return semestr;
    }

    public void setSemestr(String semestr) {
        this.semestr = semestr;
    }

    public int getRocnik() {
        return rocnik;
    }

    public void setRocnik(int rocnik) {
        this.rocnik = rocnik;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public String getZakonceni() {
        return zakonceni;
    }

    public void setZakonceni(String zakonceni) {
        this.zakonceni = zakonceni;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public ArrayList<Vyucujici> getVyucujici() {
        return vyucujici;
    }

    public void setVyucujici(ArrayList<Vyucujici> vyucujici) {
        this.vyucujici = vyucujici;
    }

    

    @Override
    public String toString() {
        return nazev + " ("+ zkratka + ") "+ plan;
    }   
}
