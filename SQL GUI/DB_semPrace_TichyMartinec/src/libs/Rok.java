/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libs;

/**
 *
 * @author ROR
 */
public class Rok {
    private int rok;
    private String rokS;

    public Rok(int rok) {
        this.rok = rok;
        this.rokS = rok+"/"+(rok+1);
    }
    
    public Rok(int rok, String rokS) {
        this.rok = rok;
        this.rokS = rokS;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public String getRokS() {
        return rokS;
    }

    public void setRokS(String rokS) {
        this.rokS = rokS;
    }

    @Override
    public String toString() {
        return rokS;
    }
    
    
}
