/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orukomm.data.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import orukomm.data.Database;


/**
 *
 * @author Johan
 */
public class UserRepository {
    
    private static Database db = new Database();
    private static Connection con = db.getConnection();
    
    public static void registerUser(JTextField txt1, JTextField txt2, JTextField txt3, JTextField txt4, JPasswordField pw1, JPasswordField pw2) 
            throws SQLException
    {
        
        String firstName = txt1.getText();
        String lastName = txt2.getText();
        String email = txt3.getText();
        String username = txt4.getText();
        String password1 = pw1.getText();
        String password2 = pw2.getText();
        
        if(password1.equals(password2))
        {
            try
            {
                String query ="INSERT INTO user VALUES(null, '" + email + "','" + firstName + "','" + lastName + "','" + password1 + "', null);";
                System.out.println(query);
                PreparedStatement ps = con.prepareStatement(query);
                ps.executeUpdate();               
                               
            } catch(SQLException e){
                System.out.println(e);
            }
        }
    }
}
