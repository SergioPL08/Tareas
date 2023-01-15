/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package interfaz.tareas2;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Sergio
 */
public class InterfazController implements Initializable {

    @FXML
    ImageView imgLogin;
    
    @FXML
    Pane clases,clase,login,barraMenu;
    
    @FXML
    Label username,password,smg;
    
    Connection conector;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conector = Conector.makeConnect();
        
    }    
    
    
    public void showImage(){
        try{
            Image image = new Image("resources/img/Login.png");
            imgLogin.setImage(image);
            imgLogin.setCache(true);
        }
        catch (Exception e){
            System.out.println("");
        }
    }
        
    
    @FXML
    public void clase(){
        clase.setVisible(true);
        barraMenu.setVisible(true);
        login.setVisible(false);
        clases.setVisible(false);
    }
    
    @FXML
    public void iniciaSesion(){
        String user = username.getText();
        String pass = password.getText();
        try {
            Statement st = conector.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM personas WHERE usuario='"+user+"' AND password ='"+pass+"'");
        } catch (SQLException ex) {
            smg.setText("Usuario o contraseña incorrectos");
        }
        
        clase.setVisible(false);
        barraMenu.setVisible(true);
        login.setVisible(false);
        clases.setVisible(true);
    }
    
    @FXML
    public void home(){
        clase.setVisible(false);
        barraMenu.setVisible(true);
        login.setVisible(false);
        clases.setVisible(true);
    }
}
