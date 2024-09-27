/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author kingt
 */
public class ConnectionManager {

    // Deklarasi atribut private Connection, String driver, url, username, dan password
    private Connection con;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/sikad";  
    private String username = "root";       
    private String password = "";  

    public Connection logOn() {
        try {
            //Load JDBC Driver
            Class.forName(driver).newInstance();
            //Buat object Connection
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }

    public void logOff() {
        try {
            //Tutup Koneksi
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
