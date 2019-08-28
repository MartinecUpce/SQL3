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
public class Vyucujici {
    private int id;
    private String jmeno;
    private String prijmeni;
    private String email;
    private String telefon;
    private Katedra katedra;

    public Vyucujici(int id, String jmeno, String prijimeni, String email, String telefon, Katedra katedra) {
        this.id = id;
        this.jmeno = jmeno;
        this.prijmeni= prijimeni;
        this.email = email;
        this.telefon = telefon;
        this.katedra = katedra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Katedra getKatedra() {
        return katedra;
    }

    public void setKatedra(Katedra katedra) {
        this.katedra = katedra;
    }    

    @Override
    public String toString() {
        return jmeno + " "+ prijmeni;  
    }
}
