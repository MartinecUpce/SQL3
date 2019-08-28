/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Obor;
import entity.Plan;
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
public class PlanManager {
    private Connection conn; 

    public PlanManager(Connection conn) {
        this.conn = conn;
    }
    
    public ArrayList<Plan> getPlanAll() throws SQLException{
        ArrayList<Plan> plany = new ArrayList<>();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM view_obor_plan");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            plany.add(new Plan(
                    rset.getInt("id_stud_planu"),
                    rset.getString("jmeno_planu"),
                    new Obor(
                        rset.getInt("id_stud_oboru"),
                        rset.getString("jmeno_oboru"),
                        rset.getInt("odhad_studentu"),
                        rset.getString("nazev_formy")
                    ),
                    new Rok(rset.getInt("rok"), rset.getInt("rok")+"/"+(rset.getInt("rok")+1))
            ));
        }
        stmt.close();
        return plany;
    }

    public void addPlan(Plan plan) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO stud_plan(stud_obor_id, jmeno_planu, rok) VALUES (?, ?, ?)");
        stmt.setInt(1, plan.getObor().getId());
        stmt.setString(2, plan.getNazev());
        stmt.setInt(3, plan.getRok().getRok());
        stmt.executeUpdate();
        conn.commit();

        stmt.close();
    }

    public void removePlanById(Plan plan) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM stud_plan WHERE id_stud_planu = ?");
        stmt.setInt(1, plan.getId());
        stmt.executeUpdate();
        conn.commit();

        stmt.close();
    }

    public String editPlanById(Plan plan) throws SQLException {
//        PreparedStatement stmt = conn.prepareStatement("UPDATE stud_plan SET stud_obor_id = ?, jmeno_planu = ?, rok = ? WHERE id_stud_planu = ?");
//        stmt.setInt(1, plan.getObor().getId());
//        stmt.setString(2, plan.getNazev());
//        stmt.setInt(3, plan.getRok().getRok());
//        stmt.setInt(4, plan.getId());
//        stmt.executeUpdate();
//        conn.commit();
//
//        stmt.close();
        String message;
        CallableStatement stmt = conn.prepareCall("{? = call func_update_plan(?, ?, ?, ?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
        stmt.setInt(2, plan.getId());
        stmt.setInt(3, plan.getObor().getId());
        stmt.setString(4, plan.getNazev());
        stmt.setInt(5, plan.getRok().getRok());
        
        stmt.executeUpdate();
        message = stmt.getString(1);
        conn.commit();
        
        stmt.close();
        return message;
    }
}
