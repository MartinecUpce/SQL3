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
public class User {
    private int id;
    private String jmeno;
    private String heslo;
    private Vyucujici vyucujici;
    private String role;
    private Obrazek obrazek;

    public User(int id, String jmeno, String heslo, String role, Obrazek obrazek) {
        this.id = id;
        this.jmeno = jmeno;
        this.heslo = heslo;
        this.role = role;
        this.vyucujici = null;
        this.obrazek = obrazek;
    }

    public User(int id, String jmeno, String heslo, String role, Obrazek obrazek, Vyucujici vyucujici) {
        this.id = id;
        this.jmeno = jmeno;
        this.heslo = heslo;
        this.role = role;
        this.vyucujici = vyucujici;
        this.obrazek = obrazek;
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

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public Vyucujici getVyucujici() {
        return vyucujici;
    }

    public void setVyucujici(Vyucujici vyucujici) {
        this.vyucujici = vyucujici;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Obrazek getObrazek() {
        return obrazek;
    }

    public void setObrazek(Obrazek obrazek) {
        this.obrazek = obrazek;
    }
    

    @Override
    public String toString() {
        return jmeno + " (" + role + ")";
    }
}
