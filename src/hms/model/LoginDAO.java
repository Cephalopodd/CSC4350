/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.model;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author jgreene
 */
public class LoginDAO {
    
    Connection c;
    Statement stmt;
    ResultSet rs;
  
    public User authenticateUser(String userName, Integer pwHash){
        
        User authenticatedUser = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            rs = stmt.executeQuery("select * from user where login like '" + userName + "'");
            if (rs.next()) {
                if (pwHash == rs.getInt("pw")) {
                    MenuType defMenu;
                    switch (rs.getString("menu")) {
                        case "res":
                            defMenu = MenuType.RESERVATIONS; break;
                        case "front":
                            defMenu = MenuType.FRONTDESK; break;
                        case "rooms":
                            defMenu = MenuType.ROOMS; break;
                        case "bill":
                            defMenu = MenuType.BILLING; break;
                        case "admin":
                            defMenu = MenuType.ADMIN; break;
                        default:
                            defMenu = MenuType.NONE;
                    }
                    authenticatedUser = new User(
                        rs.getString("login"), defMenu, rs.getBoolean("res_access"), 
                            rs.getBoolean("front_access"), rs.getBoolean("rooms_access"), 
                            rs.getBoolean("bill_access"), rs.getBoolean("admin_access"));
                }
            }
            
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return authenticatedUser;
    }

    private void closeAll() {
        try {
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    //Can I please get a query that will return the user password.
    public boolean matchPassword(String userName, Integer pwHash){
        
        User authenticatedUser = null;
        boolean passwordMatched = true;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            rs = stmt.executeQuery("select * from user where login like '" + userName + "'");
            if (rs.next()) {
                if (pwHash == rs.getInt("pw")) {
                    passwordMatched = true;
                }else{
                    passwordMatched = false;
                }
            }
            
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        
        return passwordMatched;
        
    }
}
