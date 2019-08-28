/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Fakulta;
import entity.Katedra;
import entity.Obor;
import entity.Plan;
import entity.Predmet;
import entity.Vyucujici;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import libs.Rok;

/**
 *
 * @author RoR
 */
public class PredmetManager {

    private Connection conn;

    public PredmetManager(Connection conn) {
        this.conn = conn;
    }

    public Predmet getPredmetById(int id) throws SQLException {
        OborManager oborM = new OborManager(conn);
        VyucujiciManager vyucujiciM = new VyucujiciManager(conn);

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM view_predmety_uplny WHERE id_prob = " + id);
        ResultSet rset = stmt.executeQuery();
        Predmet predmet = null;
        if (rset.next()) {
            predmet = new Predmet(
                    rset.getInt("id_prob"),
                    rset.getString("nazev_predmetu"),
                    rset.getString("zkratka_predmetu"),
                    rset.getString("semestr"),
                    rset.getInt("rocnik"),
                    rset.getString("nazev_kategorie"),
                    rset.getString("nazev_zakonceni"),
                    new Plan(
                            rset.getInt("id_stud_planu"),
                            rset.getString("jmeno_planu"),
                            oborM.getOborById(rset.getInt("stud_obor_id")),
                            new Rok(rset.getInt("rok"), rset.getInt("rok") + "/" + (rset.getInt("rok") + 1))
                    ),
                    vyucujiciM.getVyucujiciByPredmetAll(rset.getInt("id_prob"))
            );
        }
        stmt.close();
        return predmet;
    }

    public ArrayList<Predmet> getPredmetAll() throws SQLException {
        ArrayList<Predmet> predmety = new ArrayList<>();
        OborManager oborM = new OborManager(conn);
        VyucujiciManager vyucujiciM = new VyucujiciManager(conn);

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM view_predmety_uplny");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            predmety.add(new Predmet(
                    rset.getInt("id_prob"),
                    rset.getString("nazev_predmetu"),
                    rset.getString("zkratka_predmetu"),
                    rset.getString("semestr"),
                    rset.getInt("rocnik"),
                    rset.getString("nazev_kategorie"),
                    rset.getString("nazev_zakonceni"),
                    new Plan(
                            rset.getInt("id_stud_planu"),
                            rset.getString("jmeno_planu"),
                            oborM.getOborById(rset.getInt("stud_obor_id")),
                            new Rok(rset.getInt("rok"), rset.getInt("rok") + "/" + (rset.getInt("rok") + 1))
                    ),
                    vyucujiciM.getVyucujiciByPredmetAll(rset.getInt("id_prob"))
            ));
        }
        stmt.close();
        return predmety;
    }

    public String addPredmet(Predmet predmet) throws SQLException {
        String message;
        CallableStatement stmt = conn.prepareCall("{call proc_vloz_predmet(?, ?, ?, ?)}");
        stmt.setString(1, predmet.getNazev());
        stmt.setString(2, predmet.getZkratka());
        stmt.setString(3, predmet.getSemestr());
        stmt.setString(4, predmet.getZakonceni());

        stmt.execute();
        conn.commit();
        
        stmt = conn.prepareCall("{? = call func_vloz_pvp2(?, ?, ?, ?, ?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
        stmt.setString(2, predmet.getZkratka());
        stmt.setString(3, predmet.getZakonceni());
        stmt.setString(4, predmet.getKategorie());
        stmt.setInt(5, predmet.getPlan().getId());
        stmt.setInt(6, predmet.getRocnik());

        stmt.executeUpdate();
        message = stmt.getString(1);
        conn.commit();
        
        stmt.close();
        return message;
    }

    public void removePredmetById(Predmet predmet) throws SQLException {
//        PreparedStatement stmt = conn.prepareStatement("DELETE FROM predmet_v_planu WHERE id_prob = ?");
//        stmt.setInt(1, predmet.getId());
//        stmt.executeUpdate();
//        conn.commit();
//
//        stmt.close();

        CallableStatement stmt = conn.prepareCall("{call proc_delete_predmet(?)}");
        stmt.setInt(1, predmet.getId());

        stmt.execute();
        conn.commit();
    }

    public String editPredmetById(Predmet predmet) throws SQLException {
        String message;
        CallableStatement stmt = conn.prepareCall("{call proc_update_predmet(?, ?, ?, ?, ?)}");
        stmt.setInt(1, predmet.getId());
        stmt.setString(2, predmet.getNazev());
        stmt.setString(3, predmet.getZkratka());
        stmt.setString(4, predmet.getSemestr());
        stmt.setString(5, predmet.getZakonceni());

        stmt.execute();
        conn.commit();
        
        stmt = conn.prepareCall("{? = call func_update_pvp(?, ?, ?, ?, ?, ?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
        stmt.setInt(2, predmet.getId());
        stmt.setString(3, predmet.getZkratka());
        stmt.setString(4, predmet.getZakonceni());
        stmt.setString(5, predmet.getKategorie());
        stmt.setInt(6, predmet.getPlan().getId());
        stmt.setInt(7, predmet.getRocnik());

        System.out.println(predmet.getId()+","+predmet.getZkratka()+", "+predmet.getZakonceni()+", "+predmet.getKategorie()+", "+predmet.getPlan().getId()+", "+predmet.getRocnik());
        
        
        stmt.executeUpdate();
        message = stmt.getString(1);
        conn.commit();
        
        stmt.close();
        return message;
    }

    public String addVyucujicToPredmet(Vyucujici vyucujici, Predmet predmet, String role, int hodiny) throws SQLException {
        String message;
        CallableStatement stmt = conn.prepareCall("{? = call func_vloz_uci(?, ?, ?, ?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
        stmt.setInt(2, vyucujici.getId());
        stmt.setInt(3, predmet.getId());
        stmt.setString(4, role);
        stmt.setInt(5, hodiny);
        
        stmt.executeUpdate();
        message = stmt.getString(1);
        conn.commit();
        
        stmt.close();
        return message;
    }

    public void removeVyucujiciFromPredmet(Vyucujici vyucujici, Predmet predmet) throws SQLException {
        CallableStatement stmt = conn.prepareCall("{call proc_delete_vlozi2(?, ?)}");
        stmt.setInt(1, vyucujici.getId());
        stmt.setInt(2, predmet.getId());

        stmt.execute();
        conn.commit();
    }
}
