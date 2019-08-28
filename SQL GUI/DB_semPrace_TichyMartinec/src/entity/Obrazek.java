/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author ROR
 */
public class Obrazek {
    private int id;
    private String typ;
    private String pripona;
    private Date datum;
    private InputStream obrazek;
    private Image obrazekImage;

    public Obrazek(int id, String typ, String pripona, Date datum, InputStream obrazek) {
        this.id = id;
        this.typ = typ;
        this.pripona = pripona;
        this.datum = datum;
        this.obrazek = obrazek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getPripona() {
        return pripona;
    }

    public void setPripona(String pripona) {
        this.pripona = pripona;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public InputStream getObrazek() {
        return obrazek;
    }

    public void setObrazek(InputStream obrazek) throws IOException {
        //this.obrazekImage = SwingFXUtils.toFXImage(ImageIO.read(obrazek), null);
        this.obrazek = obrazek;
    }

    public Image getObrazekImage() {
        return obrazekImage;
    }
    
}
