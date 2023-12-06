/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMessages;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class koneksi {

    private Connection koneksi;
    public Connection connect(){
        try{
            String url = "jdbc:mysql://localhost/db_kp";
            koneksi = (Connection) DriverManager.getConnection(url, "root", "");
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Koneksi Gagal"+e.getMessage());
        }
        return koneksi;
    }
}
