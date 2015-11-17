/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.model;

import java.sql.*;
import java.time.LocalDate;
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

    public ObservableList queryCharges(int confirmation) {

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
}
