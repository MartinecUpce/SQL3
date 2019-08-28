/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import db.DatabaseManager;
import db.UserManager;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libs.AlertWindow;

/**
 * FXML Controller class
 *
 * @author ROR
 */
public class FXMLLoginController implements Initializable {

    private TextField textEmail;
    @FXML
    private Button buttonLogin;
    @FXML
    private PasswordField textPassword;
    
    private DatabaseManager db;
    @FXML
    private TextField textUsername;
    
    private static User loggedUser;
    private static Connection conn;
    private static boolean btnClicked = false;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.db = new DatabaseManager("usr", "passw");
        } catch (SQLException ex) {
            AlertWindow.showError("Error", "SQLError", ex.toString());
            Platform.exit();
        } 
    }    

    @FXML
    private void loginAction(ActionEvent event) throws SQLException, IOException {
        String username = textUsername.getText();
        String password = textPassword.getText();
        
        UserManager userM = new UserManager(db.getConn());
        if (userM.login(username, password)) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMain.fxml"));
            
            this.loggedUser = userM.getLoggedUser();
            this.btnClicked = true;
            this.conn = db.getConn();
            
            Parent root = (Parent) loader.load();
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Semestrální práce MT, VM- Logged as "+userM.getLoggedUser().getJmeno()+" ("+userM.getLoggedUser().getRole()+")");
            
            ((Stage) buttonLogin.getScene().getWindow()).hide();
            stage.showAndWait();
        }else{
            AlertWindow.showError("Login error", "Neúspěšné přihlášení", "Špatné uživatelské jméno nebo heslo!");
        }
    } 

    public static void setLoggedUser(User loggedUser) {
        FXMLLoginController.loggedUser = loggedUser;
    }

    public static void setConn(Connection conn) {
        FXMLLoginController.conn = conn;
    }

    public static void setBtnClicked(boolean btnClicked) {
        FXMLLoginController.btnClicked = btnClicked;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static Connection getConn() {
        return conn;
    }

    public static boolean isBtnClicked() {
        return btnClicked;
    }


    
}
