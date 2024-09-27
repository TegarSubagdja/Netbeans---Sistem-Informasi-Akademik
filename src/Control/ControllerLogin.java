/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.Akun;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import Model.ConnectionManager;
import View.Index_Mahasiswa;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kingt
 */
public class ControllerLogin {

    public Akun VerifikasiLogin(String username, String password) {
        String query = "SELECT * FROM akun_mhs WHERE (username = '" + username + "' OR nim = '" + username + "') AND password = '" + password + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        Akun acc = null; 
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                acc = new Akun();
                acc.setId(rs.getInt("nim"));
                acc.setJenis(rs.getString("jenis"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger( ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return acc;
    }
}
