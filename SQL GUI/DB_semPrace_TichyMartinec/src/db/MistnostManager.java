/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Fakulta;
import entity.Katedra;
import entity.Mistnost;
import entity.Obor;
import entity.Vyucujici;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author RoR
 */
public class MistnostManager {
    private Connection conn; 

    public MistnostManager(Connection conn) {
        this.conn = conn;
    }
    
    public Mistnost getMistnostById(int id) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM mistnost WHERE id_mistnosti = "+id);
        ResultSet rset = stmt.executeQuery();
        Mistnost mistnost = null;
        if (rset.next()) {
            mistnost = new Mistnost(
                    rset.getInt("id_mistnosti"),
                    rset.getInt("kapacita_mistnosti"),
                    rset.getString("oznaceni")
            );
        }
        stmt.close();
        return mistnost;
    }
    
    public ArrayList<Mistnost> getMistnostAll() throws SQLException{
        ArrayList<Mistnost> mistnosti = new ArrayList<>();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM mistnost");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            mistnosti.add(new Mistnost(
                    rset.getInt("id_mistnosti"),
                    rset.getInt("kapacita_mistnosti"),
                    rset.getString("oznaceni")
            ));
        }
        stmt.close();
        return mistnosti;
    }

    public void addMistnost(Mistnost mistnost) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO mistnost(kapacita_mistnosti, oznaceni) VALUES (?, ?)");
        stmt.setInt(1, mistnost.getKapacita());
        stmt.setString(2, mistnost.getOznaceni());
        stmt.executeUpdate();
        conn.commit();

        stmt.close();
    }
    
        
    public void removeMistnostById(Mistnost mistnost) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM mistnost WHERE id_mistnosti = ?");
        stmt.setInt(1, mistnost.getId());
        stmt.executeUpdate();
        conn.commit();

        stmt.close();
    }
    
    public void editMistnostById(Mistnost mistnost) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE mistnost SET kapacita_mistnosti = ?, oznaceni = ? WHERE id_mistnosti = ?");
        stmt.setInt(1, mistnost.getKapacita());
        stmt.setString(2, mistnost.getOznaceni());
        stmt.setInt(3, mistnost.getId());
        stmt.executeUpdate();
        conn.commit();

        stmt.close();
    }

    public void addMistnostFromFile(File file) throws FileNotFoundException, SQLException, ParserConfigurationException, SAXException, IOException{
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(file);
			
	doc.getDocumentElement().normalize();
			
	NodeList nodeList = doc.getElementsByTagName("mistnostInfo");

	for (int temp = 0; temp < nodeList.getLength(); temp++) {

		Node nNode = nodeList.item(temp);
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

                        this.addMistnost(new Mistnost(
                                0,
                                Integer.valueOf(eElement.getElementsByTagName("kapacita").item(0).getTextContent()),
                                eElement.getElementsByTagName("zkrBudovy").item(0).getTextContent()+"-"+eElement.getElementsByTagName("cisloMistnosti").item(0).getTextContent()
                        ));
		}
        }
    }
}
