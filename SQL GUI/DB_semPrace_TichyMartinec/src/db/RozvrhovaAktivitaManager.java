/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Obor;
import entity.RozvrhovaAktivita;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import libs.Rok;

/**
 *
 * @author ROR
 */
public class RozvrhovaAktivitaManager {
    private Connection conn; 

    public RozvrhovaAktivitaManager(Connection conn) {
        this.conn = conn;
    }
    
    public ArrayList<RozvrhovaAktivita> getRozvrhovaAktivitaAll() throws SQLException{
        ArrayList<RozvrhovaAktivita> aktivity = new ArrayList<>();
        PredmetManager predmetM = new PredmetManager(conn);
        VyucujiciManager vyucujiciM = new VyucujiciManager(conn);
        MistnostManager mistnostM = new MistnostManager(conn);

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM view_rozvrhova_akce2");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            aktivity.add(new RozvrhovaAktivita(
                    rset.getInt("id_aktivity"),
                    rset.getString("nazev_tyden"),
                    rset.getInt("od"),
                    rset.getInt("do"),
                    rset.getInt("schvaleno"),
                    rset.getString("nazev"),
                    rset.getInt("kapacita_akce"),
                    predmetM.getPredmetById(rset.getInt("id_prob")),
                    rset.getString("nazev_zpusobu"),
                    vyucujiciM.getVyucujiciById(rset.getInt("id_vyucujici")),
                    mistnostM.getMistnostById(rset.getInt("id_mistnosti"))
            ));
        }
        stmt.close();
        return aktivity;
    }
    
    public ArrayList<RozvrhovaAktivita> getRozvrhovaAktivitaAllByVyucujiciRok(int id, int rok) throws SQLException{
        ArrayList<RozvrhovaAktivita> aktivity = new ArrayList<>();
        PredmetManager predmetM = new PredmetManager(conn);
        VyucujiciManager vyucujiciM = new VyucujiciManager(conn);
        MistnostManager mistnostM = new MistnostManager(conn);

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM view_rozvrhova_akce2 WHERE id_vyucujici = "+id+" AND rok = "+rok);
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            aktivity.add(new RozvrhovaAktivita(
                    rset.getInt("id_aktivity"),
                    rset.getString("nazev_tyden"),
                    rset.getInt("od"),
                    rset.getInt("do"),
                    rset.getInt("schvaleno"),
                    rset.getString("nazev"),
                    rset.getInt("kapacita_akce"),
                    predmetM.getPredmetById(rset.getInt("id_prob")),
                    rset.getString("nazev_zpusobu"),
                    vyucujiciM.getVyucujiciById(rset.getInt("id_vyucujici")),
                    mistnostM.getMistnostById(rset.getInt("id_mistnosti"))
            ));
        }
        stmt.close();
        return aktivity;
    }

    public String addRozvrhAktivita(RozvrhovaAktivita rozvrhovaAktivita, boolean admin) throws SQLException {
        String message;
        CallableStatement stmt;
        if (admin) {
            stmt = conn.prepareCall("{? = call func_vloz_rozvrh(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        }else{
            stmt = conn.prepareCall("{? = call func_vloz_rozvrh_admin(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        }
        
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
        stmt.setInt(2, rozvrhovaAktivita.getKapacita());
        stmt.setInt(3, rozvrhovaAktivita.getMistnost().getId());
        stmt.setString(4, rozvrhovaAktivita.getTyden());
        stmt.setInt(5, rozvrhovaAktivita.getZacatek());
        stmt.setInt(6, rozvrhovaAktivita.getKonec());
        stmt.setString(7, rozvrhovaAktivita.getZpusobVyuky());
        stmt.setString(8, rozvrhovaAktivita.getDen());
        stmt.setInt(9, rozvrhovaAktivita.getVyucujici().getId());
        stmt.setInt(10, rozvrhovaAktivita.getPredmet().getId());
        stmt.setInt(11, rozvrhovaAktivita.getPredmet().getPlan().getRok().getRok());
        stmt.setInt(12, rozvrhovaAktivita.getSchvaleno());

        stmt.executeUpdate();
        message = stmt.getString(1);
        conn.commit();
        
        return message;
    }

    public void removeRozvrhovaAktivitaById(RozvrhovaAktivita rozvrhovaAktivita) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM rozvrhova_aktivita WHERE id_aktivity = ?");
        stmt.setInt(1, rozvrhovaAktivita.getId());
        stmt.executeUpdate();
        conn.commit();

        stmt.close();
    }

    public String editRozvrhovaAktivitaById(RozvrhovaAktivita rozvrhovaAktivita, boolean admin) throws SQLException {
        String message;
        CallableStatement stmt;
        if (admin) {
            stmt = conn.prepareCall("{? = call func_uprav_rozvrh_admin(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        }else {
            stmt = conn.prepareCall("{? = call func_update_rozvrh(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        }
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
        stmt.setInt(2, rozvrhovaAktivita.getId());
        stmt.setInt(3, rozvrhovaAktivita.getKapacita());
        stmt.setInt(4, rozvrhovaAktivita.getMistnost().getId());
        stmt.setString(5, rozvrhovaAktivita.getTyden());
        stmt.setInt(6, rozvrhovaAktivita.getZacatek());
        stmt.setInt(7, rozvrhovaAktivita.getKonec());
        stmt.setString(8, rozvrhovaAktivita.getZpusobVyuky());
        stmt.setString(9, rozvrhovaAktivita.getDen());
        stmt.setInt(10, rozvrhovaAktivita.getVyucujici().getId());
        stmt.setInt(11, rozvrhovaAktivita.getPredmet().getId());
        stmt.setInt(12, rozvrhovaAktivita.getPredmet().getPlan().getRok().getRok());
        stmt.setInt(13, rozvrhovaAktivita.getSchvaleno());

        stmt.executeUpdate();
        message = stmt.getString(1);
        conn.commit();
        
        return message;
    }
}
