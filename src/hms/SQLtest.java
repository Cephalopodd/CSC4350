/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

/**
 *
 * @author Thomas
 */
import java.sql.*;

public class SQLtest
{
  public static void main( String args[] )
  {}
  
  public SQLtest(){
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:hms.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM GUEST;" );
      while ( rs.next() ) {
         int id = rs.getInt("id");
         String  name = rs.getString("fname") + " " + rs.getString("lname");
         String phone = rs.getString("phone");
         String email = rs.getString("email");
         String address  = rs.getString("addr1") + " " + rs.getString("addr2") + " " + 
            rs.getString("city") + " " + rs.getString("state") + " " + rs.getString("zip");
         Boolean vip = rs.getBoolean("vip");
         String notes = rs.getString("notes");
         System.out.println( "ID = " + id );
         System.out.println( "NAME = " + name );
         System.out.println( "PHONE = " + phone );
         if (!email.equals("")) {
             System.out.println( "EMAIL = " + email );
         }
         System.out.println( "ADDRESS = " + address );
         if (vip) {
            System.out.println( "VIP GUEST" );    
         }
         if (!notes.equals("")) {
             System.out.println( "NOTES: " + notes );
         }
         
         System.out.println();
      }
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Operation done successfully");
  }
}