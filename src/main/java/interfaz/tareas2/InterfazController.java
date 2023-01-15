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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Sergio
 */
public class InterfazController implements Initializable {

    @FXML
    private HBox barraMenu;
    @FXML
    private ToolBar menu;
    @FXML
    private Pane clases;
    @FXML
    private Pane clase;
    @FXML
    private Pane login;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label crearCuenta;
    @FXML
    private ImageView imgLogin;
    @FXML
    private Label msg;

    Connection conector;
    @FXML
    private ImageView clase1;
    @FXML
    private ImageView clase2;
    @FXML
    private ImageView clase7;
    @FXML
    private ImageView clase8;
    @FXML
    private ImageView clase9;
    @FXML
    private Label smg2;
    
    String user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conector = Conector.makeConnect();
        clase.setVisible(false);
        barraMenu.setVisible(false);
        login.setVisible(true);
        clases.setVisible(false);
        imagenes();
    }
    
    @FXML
    public void clase(){
        clase.setVisible(true);
        barraMenu.setVisible(true);
        login.setVisible(false);
        clases.setVisible(false);
        cambiaTamaño(880,600);
    }
    
    @FXML
    public void iniciaSesion(){
        user = username.getText();
        String pass = password.getText();
        
        try {
            String consulta = "SELECT * FROM personas WHERE usuario='"+user+"' AND password ='"+pass+"'";
            //System.out.println(consulta);
            Statement st = conector.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(consulta);
            if(rs.first()){
                System.out.println("xd");
                clase.setVisible(false);
                barraMenu.setVisible(true);
                login.setVisible(false);
                clases.setVisible(true);
                cambiaTamaño(880,600);
                smg2.setText("Bienvenido "+user);
            }
            else{
                msg.setText("Usuario o contraseña incorrectos");
            }
        } catch (SQLException ex) {
            msg.setText("Usuario o contraseña incorrectos");
        }
        
        
    }
    
    @FXML
    public void home(){
        clase.setVisible(false);
        barraMenu.setVisible(true);
        login.setVisible(false);
        clases.setVisible(true);
        cambiaTamaño(880,600);
        smg2.setText("Bienvenido "+user);
    }
    
    @FXML
    private void salir(ActionEvent event) {
        clase.setVisible(false);
        barraMenu.setVisible(false);
        login.setVisible(true);
        clases.setVisible(false);
        cambiaTamaño(729,435);
    }
    
    public void imagenes() {
        Image image1 = new Image(getClass().getResourceAsStream("/img/Login.png"));
        ImageView imageView1 = new ImageView(image1);
        Image image2 = new Image(getClass().getResourceAsStream("/img/interfaces.png"));
        ImageView imageView2 = new ImageView(image2);
        imgLogin.setImage(image1);
        clase1.setImage(image2);
    }

    public void cambiaTamaño(int width, int height){
        Window win = App.getScene().getWindow();
        win.setWidth(width);
        win.setHeight(height);
    }
}
