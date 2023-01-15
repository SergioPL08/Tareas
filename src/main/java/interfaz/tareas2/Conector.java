/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.tareas2;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Sergio
 */
public class Conector {
    // Para establecer la conexión con la base de datos tenemos que seguir la siguiente estructura: jdbc:mysql://[host][:puerto]/[DB]
    private String host;
    private String port;
    private String dbName;
    private String user;
    private String pass;
    Connection conex;
    
    public Conector(String host, String puerto, String dbName, String user, String pass) {
        this.host = host;
        this.port = puerto;
        this.dbName = dbName;
        this.user = user;
        this.pass = pass;
    }

// Para usarlo con MariaDB en una base de datos local llamada javanet  -> Connnection conex = new Conexion("localhost","3306","javanet","javanet","1234qwerty").makeconnect();
    
    public Connection makeConnect(){
        try{
            //jdbc:mysql://localhost:3306/zoo?zeroDateTimeBehavior=CONVERT_TO_NULL [zoologico on Default schema]
            String url="jdbc:mysql://"+this.host+":"+this.port+"/"+this.dbName;
            Connection conex = DriverManager.getConnection(url, this.user, this.pass);
            return conex;
        }catch(SQLException ex){
            System.err.println("Error al establecer la conexion "+ex.getMessage());
            return null;
        }
        
    }
    public static void closeConnect(Connection con){
        try {
            // Cerramos posibles conexiones abiertas
            if (con!=null) con.close();    
          } catch (Exception e) {
            System.out.println("Error cerrando conexiones: " + e.toString());
          } 
    }
    
    public static ResultSet comprobarDatos(String consulta, Connection conDB){
        try {
            Statement st = conDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(consulta);
            if(!rs.first()){
                return null;
            }
            else{
                return rs;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ResultSet getSelect(String consulta, Connection conDB){
        try {
            Statement st = conDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(consulta);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ResultSet insertTable(String consulta, Connection conDB){
        try {
            Statement st = conDB.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            rs.moveToInsertRow();
            rs.updateString(port, port);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static int editTable(String consulta, Connection conDB){
        try{
            Statement st = conDB.createStatement();
            int rs = st.executeUpdate(consulta);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public static ResultSet getTabla(int resultSetType, int resultSetConcurrence, String consulta, Connection conDB){
        try {
            Statement smt = conDB.createStatement(resultSetType,resultSetConcurrence);
            ResultSet rs = smt.executeQuery(consulta);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
   
}
