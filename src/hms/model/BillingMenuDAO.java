/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author admin
 */
public class BillingMenuDAO {

    Connection c;
    Statement stmt;
    ResultSet rs;

    private void closeAll() {
        try {
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public ObservableList<FolioCharge> queryCharges(int confirmation) {

        //Receive Reservation confirmation number
        //Return Observable list of pending Folio Charges
        ObservableList<FolioCharge> result = FXCollections.observableArrayList();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            rs = stmt.executeQuery("select code, descrip, date, amount "
                    + "from charge where r_id = " + confirmation);
            while (rs.next()) {
                result.add(new FolioCharge(
                    rs.getString("code"),
                    rs.getString("descrip"),
                    rs.getString("date"),
                    rs.getDouble("amount")));
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return result;
    }
    
    public ObservableList<BillingMenuDTO> queryCurrentGuests() {
        
        ObservableList<BillingMenuDTO> result = FXCollections.observableArrayList();
        String sql = "select r.id, g.fname, g.lname, r.rm_num from guest as g, "
                + "reservation as r where g.id=r.g_id and r.status like 'CHECKEDIN'";

        System.out.println(sql);
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                result.add(new BillingMenuDTO(
                        rs.getInt(1), 
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4))
                );
            }
              
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return result;
    }

    public ObservableList<Item> queryItems() {
        
        ObservableList result = FXCollections.observableArrayList();
        
        ArrayList<String> itemList = new ArrayList();
        itemList.add("Parking,Valet Parking is available 24 hrs a day,PARKING,25.00");
        itemList.add("Spa1,Spa package 30 minutes,SPA,50.00");
        itemList.add("Spa2,Spa package 60 minutes,SPA,100.00");
        itemList.add("Spa3,Spa package 120 minutes,SPA,125.00");
        itemList.add("Continental Breakfast,Plain breakfast served up daily from 7:00 to 9:00,FOOD,10.00");
        itemList.add("Deluxe Breakfast,Deluxe breakfast,FOOD,15.00");
        itemList.add("Parking,Valet Parking is available 24 hrs a day,PARKING,25.00");
        itemList.add("Spa1,Spa package 30 minutes,SPA,50.00");
        itemList.add("Spa2,Spa package 60 minutes,SPA,100.00");
        itemList.add("Spa3,Spa package 120 minutes,SPA,125.00");
        itemList.add("Continental Breakfast,Plain breakfast served up daily from 7:00 to 9:00,FOOD,10.00");
        itemList.add("Deluxe Breakfast,Deluxe breakfast,FOOD,15.00");
        itemList.add("Parking,Valet Parking is available 24 hrs a day,PARKING,25.00");
        itemList.add("Spa1,Spa package 30 minutes,SPA,50.00");
        itemList.add("Spa2,Spa package 60 minutes,SPA,100.00");
        itemList.add("Spa3,Spa package 120 minutes,SPA,125.00");
        itemList.add("Continental Breakfast,Plain breakfast served up daily from 7:00 to 9:00,FOOD,10.00");
        itemList.add("Deluxe Breakfast,Deluxe breakfast,FOOD,15.00");
        itemList.add("Parking,Valet Parking is available 24 hrs a day,PARKING,25.00");
        itemList.add("Spa1,Spa package 30 minutes,SPA,50.00");
        itemList.add("Spa2,Spa package 60 minutes,SPA,100.00");
        itemList.add("Spa3,Spa package 120 minutes,SPA,125.00");
        itemList.add("Continental Breakfast,Plain breakfast served up daily from 7:00 to 9:00,FOOD,10.00");
        itemList.add("Deluxe Breakfast,Deluxe breakfast,FOOD,15.00");

        
        for ( String s : itemList) {
              String [] sa = s.split(",");
              Item item = new Item(sa[0],sa[1],sa[2],Double.parseDouble(sa[3]));
              result.add(item);
        }
        
        return result;
    }
    
}
